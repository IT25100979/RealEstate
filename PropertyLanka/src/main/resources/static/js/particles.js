(function () {
      const canvas = document.getElementById('particle-canvas');
      const ctx    = canvas.getContext('2d');

      /*  Resize canvas to fill hero  */
      function resize() {
        canvas.width  = canvas.offsetWidth;
        canvas.height = canvas.offsetHeight;
      }
      resize();
      window.addEventListener('resize', resize);

      /*  Particle factory  */
      const PURPLE  = [139, 92, 246];
      const VIOLET  = [109, 40, 217];
      const WHITE   = [220, 210, 255];

      const palettes = [PURPLE, VIOLET, WHITE, PURPLE, WHITE];

      function randomBetween(a, b) { return a + Math.random() * (b - a); }

      class Particle {
        constructor() { this.reset(true); }

        reset(initialSpread) {
          this.x     = randomBetween(0, canvas.width);
          this.y     = initialSpread
                        ? randomBetween(0, canvas.height)
                        : canvas.height + 10;
          this.r     = randomBetween(1.2, 3.8);
          this.speed = randomBetween(0.15, 0.55);
          this.drift = randomBetween(-0.18, 0.18);
          this.alpha = 0;
          this.maxAlpha = randomBetween(0.35, 0.85);
          this.life  = 0;
          this.maxLife = randomBetween(180, 380);
          const col  = palettes[Math.floor(Math.random() * palettes.length)];
          this.r_col = col[0];
          this.g_col = col[1];
          this.b_col = col[2];
          /* glow radius = 2–5× particle radius */
          this.glowR = this.r * randomBetween(2.5, 5);
        }

        update() {
          this.y    -= this.speed;
          this.x    += this.drift;
          this.life += 1;

          /* fade in first 15% of life, fade out last 20% */
          const progress = this.life / this.maxLife;
          if (progress < 0.15) {
            this.alpha = (progress / 0.15) * this.maxAlpha;
          } else if (progress > 0.80) {
            this.alpha = ((1 - progress) / 0.20) * this.maxAlpha;
          } else {
            this.alpha = this.maxAlpha;
          }

          if (this.life >= this.maxLife) this.reset(false);
        }

        draw() {
          /* outer glow */
          const grd = ctx.createRadialGradient(
            this.x, this.y, 0,
            this.x, this.y, this.glowR
          );
          grd.addColorStop(0,   `rgba(${this.r_col},${this.g_col},${this.b_col},${this.alpha * 0.55})`);
          grd.addColorStop(0.4, `rgba(${this.r_col},${this.g_col},${this.b_col},${this.alpha * 0.20})`);
          grd.addColorStop(1,   `rgba(${this.r_col},${this.g_col},${this.b_col},0)`);

          ctx.beginPath();
          ctx.arc(this.x, this.y, this.glowR, 0, Math.PI * 2);
          ctx.fillStyle = grd;
          ctx.fill();

          /* core dot */
          ctx.beginPath();
          ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2);
          ctx.fillStyle = `rgba(${this.r_col},${this.g_col},${this.b_col},${this.alpha})`;
          ctx.fill();
        }
      }

      /* ── Create particle pool ── */
      const COUNT = 72;
      const particles = Array.from({ length: COUNT }, () => new Particle());

      /*  Mouse parallax */
      let mx = -9999, my = -9999;
      canvas.parentElement.addEventListener('mousemove', e => {
        const rect = canvas.getBoundingClientRect();
        mx = e.clientX - rect.left;
        my = e.clientY - rect.top;
      });
      canvas.parentElement.addEventListener('mouseleave', () => { mx = -9999; my = -9999; });

      /* Connecting lines between nearby particles  */
      function drawConnections() {
        const MAX_DIST = 90;
        for (let i = 0; i < particles.length; i++) {
          for (let j = i + 1; j < particles.length; j++) {
            const dx   = particles[i].x - particles[j].x;
            const dy   = particles[i].y - particles[j].y;
            const dist = Math.sqrt(dx * dx + dy * dy);
            if (dist < MAX_DIST) {
              const opacity = (1 - dist / MAX_DIST) * 0.12;
              ctx.beginPath();
              ctx.moveTo(particles[i].x, particles[i].y);
              ctx.lineTo(particles[j].x, particles[j].y);
              ctx.strokeStyle = `rgba(139,92,246,${opacity})`;
              ctx.lineWidth = 0.6;
              ctx.stroke();
            }
          }
        }
      }

      /*  Repel from mouse  */
      function applyMouseRepel(p) {
        if (mx < 0) return;
        const dx   = p.x - mx;
        const dy   = p.y - my;
        const dist = Math.sqrt(dx * dx + dy * dy);
        const REPEL = 80;
        if (dist < REPEL && dist > 0) {
          const force = (REPEL - dist) / REPEL;
          p.x += (dx / dist) * force * 1.4;
          p.y += (dy / dist) * force * 1.4;
        }
      }

      /* Render loop */
      function loop() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawConnections();
        particles.forEach(p => {
          applyMouseRepel(p);
          p.update();
          p.draw();
        });
        requestAnimationFrame(loop);
      }

      loop();
    })();
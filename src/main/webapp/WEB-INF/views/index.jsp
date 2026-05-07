<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>NestVault — Find Your Dream Property</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>

  <!-- ── TOAST ── -->
  <div class="toast" id="toast"></div>

  <!-- ══════════════════════════════════
       NAVIGATION
  ══════════════════════════════════ -->
  <nav class="nav">
    <a href="index.jsp" class="nav-logo">Nest<span>Vault</span></a>
    <div class="nav-links">
      <a href="index.jsp"       class="nav-link active">Home</a>
      <a href="properties.jsp"  class="nav-link">Properties</a>
      <a href="search.jsp"      class="nav-link">Search</a>
    </div>
    <div class="nav-right" id="nav-right">
      <!-- Injected by nav.js depending on login state -->
      <a href="login.jsp"><button class="btn-ghost">Sign In</button></a>
      <a href="register.jsp"><button class="btn-primary">Sign Up</button></a>
    </div>
  </nav>

  <!-- ══════════════════════════════════
       HERO — with particle canvas
  ══════════════════════════════════ -->
  <section class="hero">

    <!-- Particle animation lives here -->
    <canvas id="particle-canvas"></canvas>

    <div class="hero-content">
      <div class="hero-eyebrow">
        <span class="hero-pulse"></span>
        142 properties listed today
      </div>

      <h1>Find Your Next<br><span>Dream Property</span></h1>

      <p class="hero-subtitle">
        Browse, list, and connect. NestVault brings buyers, renters
        and sellers together in one sleek platform.
      </p>

      <div class="hero-ctas">
        <a href="properties.jsp">
          <button class="btn-primary-lg">Browse Properties</button>
        </a>
        <a href="add-property.jsp">
          <button class="btn-outline-lg">List Your Property</button>
        </a>
      </div>

      <div class="hero-stats">
        <div class="hero-stat">
          <div class="hero-stat-num">142</div>
          <div class="hero-stat-lbl">Total Listings</div>
        </div>
        <div class="hero-stat">
          <div class="hero-stat-num">89</div>
          <div class="hero-stat-lbl">Available Now</div>
        </div>
        <div class="hero-stat">
          <div class="hero-stat-num">58</div>
          <div class="hero-stat-lbl">For Rent</div>
        </div>
        <div class="hero-stat">
          <div class="hero-stat-num">31</div>
          <div class="hero-stat-lbl">For Sale</div>
        </div>
      </div>
    </div>
  </section>

  <!-- ══════════════════════════════════
       QUICK SEARCH BAR
  ══════════════════════════════════ -->
  <div class="quick-search">
    <div class="quick-search-inner">
      <div class="qs-field">
        <label for="qs-loc">Location</label>
        <input id="qs-loc" type="text" placeholder="City, state or ZIP...">
      </div>
      <div class="qs-divider"></div>
      <div class="qs-field">
        <label for="qs-type">Type</label>
        <select id="qs-type">
          <option value="">Any type</option>
          <option value="house">House</option>
          <option value="apartment">Apartment</option>
          <option value="villa">Villa</option>
          <option value="commercial">Commercial</option>
          <option value="land">Land</option>
        </select>
      </div>
      <div class="qs-divider"></div>
      <div class="qs-field">
        <label for="qs-listing">Listing</label>
        <select id="qs-listing">
          <option value="">Rent or Sale</option>
          <option value="rent">For Rent</option>
          <option value="sale">For Sale</option>
        </select>
      </div>
      <a href="search.jsp">
        <button class="btn-primary" style="padding: 10px 26px;">Search</button>
      </a>
    </div>
  </div>

  <!-- ══════════════════════════════════
       LATEST LISTINGS
  ══════════════════════════════════ -->
  <section class="page-section" style="padding-top: 52px;">
    <div class="section-header">
      <h2 class="section-title">Latest Listings</h2>
      <a href="properties.jsp" class="section-link">View all →</a>
    </div>

    <div class="prop-grid">

      <!-- Card 1 -->
      <a href="property-detail.jsp?id=1" class="prop-card">
        <div class="prop-card-img">
          🏢
          <span class="listing-badge badge-rent">For Rent</span>
        </div>
        <div class="prop-card-body">
          <div class="prop-card-name">The Ritz Residences</div>
          <div class="prop-card-loc">
            <svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>
            3891 Ranchview Dr, California
          </div>
          <div class="prop-card-footer">
            <div class="prop-price">$4,200/mo</div>
            <div class="prop-tags">
              <span class="prop-tag">3 bed</span>
              <span class="prop-tag">2 bath</span>
            </div>
          </div>
          <div class="prop-card-lister">
            <div class="lister-avatar">SK</div>
            <span class="lister-name">Listed by Sarah K.</span>
          </div>
        </div>
      </a>

      <!-- Card 2 -->
      <a href="property-detail.jsp?id=2" class="prop-card">
        <div class="prop-card-img c2">
          🏡
          <span class="listing-badge badge-sale">For Sale</span>
        </div>
        <div class="prop-card-body">
          <div class="prop-card-name">CityCenterDC Apartments</div>
          <div class="prop-card-loc">
            <svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>
            2972 Westheimer Rd, Illinois
          </div>
          <div class="prop-card-footer">
            <div class="prop-price">$320,000</div>
            <div class="prop-tags">
              <span class="prop-tag">4 bed</span>
              <span class="prop-tag">3 bath</span>
            </div>
          </div>
          <div class="prop-card-lister">
            <div class="lister-avatar">MT</div>
            <span class="lister-name">Listed by Mark T.</span>
          </div>
        </div>
      </a>

      <!-- Card 3 -->
      <a href="property-detail.jsp?id=4" class="prop-card">
        <div class="prop-card-img c4">
          🏠
          <span class="listing-badge badge-sale">For Sale</span>
        </div>
        <div class="prop-card-body">
          <div class="prop-card-name">Green Valley Villa</div>
          <div class="prop-card-loc">
            <svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>
            78 Hillcrest Blvd, Texas
          </div>
          <div class="prop-card-footer">
            <div class="prop-price">$1,200,000</div>
            <div class="prop-tags">
              <span class="prop-tag">6 bed</span>
              <span class="prop-tag">5 bath</span>
            </div>
          </div>
          <div class="prop-card-lister">
            <div class="lister-avatar">CR</div>
            <span class="lister-name">Listed by Carlos R.</span>
          </div>
        </div>
      </a>

    </div>
  </section>

  <!-- ══════════════════════════════════
       PARTICLE ANIMATION SCRIPT
  ══════════════════════════════════ -->
  <script>
    (function () {
      const canvas = document.getElementById('particle-canvas');
      const ctx    = canvas.getContext('2d');

      /* ── Resize canvas to fill hero ── */
      function resize() {
        canvas.width  = canvas.offsetWidth;
        canvas.height = canvas.offsetHeight;
      }
      resize();
      window.addEventListener('resize', resize);

      /* ── Particle factory ── */
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

      /* ── Mouse parallax ── */
      let mx = -9999, my = -9999;
      canvas.parentElement.addEventListener('mousemove', e => {
        const rect = canvas.getBoundingClientRect();
        mx = e.clientX - rect.left;
        my = e.clientY - rect.top;
      });
      canvas.parentElement.addEventListener('mouseleave', () => { mx = -9999; my = -9999; });

      /* ── Connecting lines between nearby particles ── */
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

      /* ── Repel from mouse ── */
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

      /* ── Render loop ── */
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
  </script>

</body>
</html>

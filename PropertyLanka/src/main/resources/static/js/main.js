document.addEventListener("DOMContentLoaded", () => {
    // Parallax effect for floating cards on mouse move
    const cards = document.querySelectorAll('.glass-card');
    const heroSection = document.querySelector('.hero');

    if (heroSection && cards.length > 0) {
        heroSection.addEventListener('mousemove', (e) => {
            const xAxis = (window.innerWidth / 2 - e.pageX) / 50;
            const yAxis = (window.innerHeight / 2 - e.pageY) / 50;

            cards.forEach((card, index) => {
                // Different speeds for different cards based on index
                const speed = (index + 1) * 0.5;
                card.style.transform = `translate(${xAxis * speed}px, ${yAxis * speed}px) rotate(var(--r))`;
            });
        });

        // Reset transform for mouse
        heroSection.addEventListener('mouseleave', () => {
            cards.forEach(card => {
                card.style.transform = `translate(0px, 0px) rotate(var(--r))`;
                // Re-enable smooth transition for reset
                card.style.transition = 'transform 0.5s ease';
            });
        });

        // Disable transition in mouse
        heroSection.addEventListener('mouseenter', () => {
            cards.forEach(card => {
                card.style.transition = 'none';
            });
        });
    }
});
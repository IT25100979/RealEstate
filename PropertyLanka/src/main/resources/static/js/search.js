/* Status chip toggle */
    document.querySelectorAll('.filter-panel .chip').forEach(chip => {
      chip.addEventListener('click', function () {
        document.querySelectorAll('.filter-panel .chip').forEach(c => c.classList.remove('active'));
        this.classList.add('active');
        document.getElementById('status-val').value = this.dataset.val;
      });
    });


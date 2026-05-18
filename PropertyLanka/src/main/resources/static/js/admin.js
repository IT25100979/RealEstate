/* ── Tab switching ── */
    document.querySelectorAll('.admin-tab').forEach(tab => {
      tab.addEventListener('click', function () {
        document.querySelectorAll('.admin-tab').forEach(t => t.classList.remove('active'));
        document.querySelectorAll('.admin-tab-content').forEach(c => c.classList.add('hidden'));
        this.classList.add('active');
        document.getElementById('tab-' + this.dataset.tab).classList.remove('hidden');
      });
    });

    /* ── Property table search ── */
    document.getElementById('prop-search').addEventListener('input', function () {
      const q = this.value.toLowerCase();
      document.querySelectorAll('#prop-table tbody tr').forEach(row => {
        row.style.display = row.textContent.toLowerCase().includes(q) ? '' : 'none';
      });
    });

    /* ── User table search ── */
    document.getElementById('user-search').addEventListener('input', function () {
      const q = this.value.toLowerCase();
      document.querySelectorAll('#user-table tbody tr').forEach(row => {
        row.style.display = row.textContent.toLowerCase().includes(q) ? '' : 'none';
      });
    });

    /* ── Delete confirmation ── */
    function confirmDelete(type, id) {
      if (confirm('Are you sure you want to delete this ' + type + '? This cannot be undone.')) {
        /* In real app this would submit a form to Delete[Property/User]Servlet */
        showToast(type.charAt(0).toUpperCase() + type.slice(1) + ' #' + id + ' deleted.');
      }
    }

    /* ── Toast helper ── */
    function showToast(msg) {
      const t = document.getElementById('toast');
      t.textContent = msg;
      t.classList.add('show');
      setTimeout(() => t.classList.remove('show'), 3000);
    }

    // Inquiry search filter
    const inqSearch = document.getElementById('inq-search');
    if (inqSearch) {
      inqSearch.addEventListener('input', function () {
        const val = this.value.toLowerCase();
        document.querySelectorAll('#inq-table tbody tr').forEach(row => {
          row.style.display = row.textContent.toLowerCase().includes(val) ? '' : 'none';
        });
      });
    }


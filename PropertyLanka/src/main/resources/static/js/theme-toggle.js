/*
   theme-toggle.js
   Include this in every page BEFORE </body>:
     <script src="theme-toggle.js"></script>

   It reads the saved preference from localStorage,
   applies the theme on load, and wires up the toggle.
 */

(function () {
  const HTML  = document.documentElement;
  const KEY   = 'nv-theme';

  /* Apply saved theme immediately (prevents flash) */
  const saved = localStorage.getItem(KEY) || 'dark';
  HTML.setAttribute('data-theme', saved);

  /* Update every toggle on the page to match state */
  function syncToggles(theme) {
    document.querySelectorAll('.theme-checkbox').forEach(cb => {
      cb.checked = (theme === 'light');
    });
    document.querySelectorAll('.theme-thumb').forEach(thumb => {
      thumb.textContent = theme === 'light' ? '☀️' : '🌙';
    });
  }

  /* Switch theme */
  function setTheme(theme) {
    HTML.setAttribute('data-theme', theme);
    localStorage.setItem(KEY, theme);
    syncToggles(theme);
  }

  /* Wire up after DOM ready */
  document.addEventListener('DOMContentLoaded', function () {
    syncToggles(saved);

    document.querySelectorAll('.theme-checkbox').forEach(cb => {
      cb.addEventListener('change', function () {
        setTheme(this.checked ? 'light' : 'dark');
      });
    });
  });
})();

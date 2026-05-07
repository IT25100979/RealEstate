<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Create Account — NestVault</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>

  <!-- NAV -->
  <nav class="nav">
    <a href="index.jsp" class="nav-logo">Nest<span>Vault</span></a>
    <div class="nav-links">
      <a href="index.jsp"       class="nav-link">Home</a>
      <a href="properties.jsp"  class="nav-link">Properties</a>
      <a href="search.jsp"      class="nav-link">Search</a>
    </div>
    <div class="nav-right">
      <a href="login.jsp" style="font-size:13px;color:var(--txt2);">
        Already have an account? <span style="color:var(--pur)">Sign in →</span>
      </a>
    </div>
  </nav>

  <!--
    BACK-END NOTE:
    Form action: RegisterServlet (POST)
    Validate: email not already used, passwords match, all required fields filled
    On success: auto-login + redirect to index.jsp
    On failure: request.setAttribute("error", "...message...")
  -->

  <div class="auth-layout">

    <!-- LEFT — branding panel -->
    <div class="auth-left">
      <div class="nav-logo" style="font-size:24px;margin-bottom:36px;cursor:default;">
        Nest<span style="color:var(--pur)">Vault</span>
      </div>
      <h2 class="auth-heading">
        Join <span>NestVault</span><br>today — it's free
      </h2>
      <p class="auth-subtext">
        List your property for free, or find your perfect home from
        thousands of verified listings across the country.
      </p>
      <div class="auth-stats">
        <div>
          <div class="auth-stat-num">Free</div>
          <div class="auth-stat-lbl">To join</div>
        </div>
        <div>
          <div class="auth-stat-num">5 min</div>
          <div class="auth-stat-lbl">To list</div>
        </div>
        <div>
          <div class="auth-stat-num">Real</div>
          <div class="auth-stat-lbl">Contacts</div>
        </div>
      </div>
    </div>

    <!-- RIGHT — register form -->
    <div class="auth-right">
      <div class="auth-form-box">

        <h2 style="font-family:var(--font-head);font-size:22px;font-weight:800;margin-bottom:6px;">
          Create Account
        </h2>
        <p class="auth-switch">
          Already have an account? <a href="login.jsp">Sign in</a>
        </p>

        <!--
          JSP error display:
          <% if (request.getAttribute("error") != null) { %>
          <div style="background:#1c0a0a;border:1px solid var(--red);border-radius:9px;padding:10px 14px;font-size:13px;color:#f87171;margin-bottom:16px;">
            <%= request.getAttribute("error") %>
          </div>
          <% } %>
        -->

        <form action="RegisterServlet" method="POST">

          <div class="form-grid-2">
            <div class="form-group">
              <label for="firstName">First Name *</label>
              <input type="text" id="firstName" name="firstName" placeholder="Jane" required>
            </div>
            <div class="form-group">
              <label for="lastName">Last Name *</label>
              <input type="text" id="lastName" name="lastName" placeholder="Doe" required>
            </div>
          </div>

          <div class="form-group">
            <label for="email">Email Address *</label>
            <input type="email" id="email" name="email" placeholder="you@email.com" required>
          </div>

          <div class="form-group">
            <label for="phone">Phone Number</label>
            <input type="tel" id="phone" name="phone" placeholder="+1 555 000 0000">
          </div>

          <div class="form-group">
            <label for="password">Password *</label>
            <input type="password" id="password" name="password"
                   placeholder="Min. 8 characters" minlength="8" required>
          </div>

          <div class="form-group" style="margin-bottom:22px;">
            <label for="confirmPassword">Confirm Password *</label>
            <input type="password" id="confirmPassword" name="confirmPassword"
                   placeholder="Repeat your password" required>
          </div>

          <button type="submit" class="btn-primary btn-full" onclick="return checkPasswords()">
            Create Account
          </button>

          <p style="text-align:center;font-size:11px;color:var(--txt3);margin-top:18px;">
            By creating an account you agree to our Terms of Service
          </p>

        </form>
      </div>
    </div>

  </div>

  <script>
    function checkPasswords() {
      const pw  = document.getElementById('password').value;
      const cpw = document.getElementById('confirmPassword').value;
      if (pw !== cpw) {
        alert('Passwords do not match. Please try again.');
        return false;
      }
      return true;
    }
  </script>

</body>
</html>

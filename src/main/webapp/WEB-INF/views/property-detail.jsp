<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Property Detail — NestVault</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>

  <div class="toast" id="toast"></div>

  <!-- NAV -->
  <nav class="nav">
    <a href="index.jsp" class="nav-logo">Nest<span>Vault</span></a>
    <div class="nav-links">
      <a href="index.jsp"       class="nav-link">Home</a>
      <a href="properties.jsp"  class="nav-link">Properties</a>
      <a href="search.jsp"      class="nav-link">Search</a>
    </div>
    <div class="nav-right">
      <a href="login.jsp"><button class="btn-ghost">Sign In</button></a>
      <a href="register.jsp"><button class="btn-primary">Sign Up</button></a>
    </div>
  </nav>

  <!--
    BACK-END NOTE:
    This page is loaded via property-detail.jsp?id=XX
    Your servlet reads the id, loads the Property object,
    and injects all the <%= property.getXxx() %> values below.
  -->

  <div class="detail-layout">

    <!-- Back button -->
    <div style="margin-bottom: 20px;">
      <button class="btn-ghost" onclick="history.back()" style="display:inline-flex;align-items:center;gap:6px;">
        <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><polyline points="15 18 9 12 15 6"/></svg>
        Back to listings
      </button>
    </div>

    <!-- Hero image -->
    <div class="detail-img c2" id="detail-img">
      <!-- In JSP: <img src="<%= property.getImageUrl() %>" alt="..."> -->
      🏡
    </div>

    <!-- Header row: title + price -->
    <div class="detail-header">
      <div>
        <!-- JSP: <%= property.getTitle() %> -->
        <h1 class="detail-title">CityCenterDC Apartments</h1>
      </div>
      <!-- JSP: $<%= property.getPrice() %> -->
      <div class="detail-price">$320,000</div>
    </div>

    <!-- Location -->
    <div class="detail-loc">
      <svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>
      <!-- JSP: <%= property.getAddress() %>, <%= property.getCity() %>, <%= property.getState() %> -->
      2972 Westheimer Rd, Illinois
    </div>

    <!-- Tags row -->
    <div class="detail-tags">
      <!-- JSP: <span class="detail-tag"><%= property.getType() %></span> etc. -->
      <span class="detail-tag">Apartment</span>
      <span class="detail-tag" style="color: var(--green);">For Sale</span>
      <span class="detail-tag">4 Bedrooms</span>
      <span class="detail-tag">3 Bathrooms</span>
      <span class="detail-tag">2,100 sq ft</span>
      <span class="status-pill status-available">Available</span>
    </div>

    <!-- Description -->
    <p class="detail-desc">
      <!-- JSP: <%= property.getDescription() %> -->
      Spacious 4-bedroom apartment in a prime Illinois location. Floor-to-ceiling windows, rooftop access and concierge service included. Steps from public transport and top-rated schools. Recently renovated kitchen and bathrooms with premium finishes throughout.
    </p>

    <!-- Contact the lister -->
    <div class="contact-card">
      <h3>Contact the Lister</h3>

      <div class="contact-row">
        <div class="contact-icon">
          <svg viewBox="0 0 24 24"><path d="M20 21v-2a4 4 0 00-4-4H8a4 4 0 00-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
        </div>
        <div>
          <div class="contact-label">Listed by</div>
          <!-- JSP: <%= property.getListerName() %> -->
          <div class="contact-value">Mark Thompson</div>
        </div>
      </div>

      <div class="contact-row">
        <div class="contact-icon">
          <svg viewBox="0 0 24 24"><path d="M22 16.92v3a2 2 0 01-2.18 2 19.79 19.79 0 01-8.63-3.07A19.5 19.5 0 013.07 9.81 19.79 19.79 0 01.22 1.17 2 2 0 012.22 0h3a2 2 0 012 1.72 12.84 12.84 0 00.7 2.81 2 2 0 01-.45 2.11L6.91 7.91a16 16 0 006.18 6.18l1.27-1.27a2 2 0 012.11-.45 12.84 12.84 0 002.81.7A2 2 0 0122 16.92z"/></svg>
        </div>
        <div>
          <div class="contact-label">Phone</div>
          <!-- JSP: <%= property.getListerPhone() %> -->
          <div class="contact-value">+1 312 555 0847</div>
        </div>
      </div>

      <div class="contact-row">
        <div class="contact-icon">
          <svg viewBox="0 0 24 24"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/><polyline points="22,6 12,13 2,6"/></svg>
        </div>
        <div>
          <div class="contact-label">Email</div>
          <!-- JSP: <%= property.getListerEmail() %> -->
          <div class="contact-value">mark.t@email.com</div>
        </div>
      </div>
    </div>

    <!--
      OWNER ACTIONS — only show if logged-in user is the lister
      JSP condition: <% if (session.getAttribute("userId").equals(property.getListerId())) { %>
    -->
    <div class="owner-actions" id="owner-actions" style="display: flex;">
      <!-- Show/hide this div using JSP session check -->
      <a href="edit-property.html?id=2">
        <button class="btn-primary">Edit Listing</button>
      </a>
      <form action="DeletePropertyServlet" method="POST" style="display:inline">
        <input type="hidden" name="propertyId" value="2">
        <button type="submit" class="btn-danger"
                onclick="return confirm('Are you sure you want to delete this listing?')">
          Delete Listing
        </button>
      </form>
    </div>
    <!-- JSP: <% } %> -->

  </div>

</body>
</html>

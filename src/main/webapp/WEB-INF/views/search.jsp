<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Search — NestVault</title>
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
      <a href="search.jsp"      class="nav-link active">Search</a>
    </div>
    <div class="nav-right">
      <a href="login.jsp"><button class="btn-ghost">Sign In</button></a>
      <a href="register.jsp"><button class="btn-primary">Sign Up</button></a>
    </div>
  </nav>

  <!-- SEARCH LAYOUT -->
  <!--
    BACK-END NOTE:
    Wrap this in a <form action="SearchServlet" method="GET">
    All filter inputs use name="" attributes ready for your servlet to read.
  -->
  <form action="SearchServlet" method="GET" class="search-layout">

    <!-- ── FILTER PANEL ── -->
    <aside class="filter-panel">
      <h3>Filter Properties</h3>

      <div class="filter-section">
        <div class="filter-label">Keyword</div>
        <!-- name="keyword" for your servlet -->
        <input class="filter-input" type="text" name="keyword" placeholder="Name or address...">
      </div>

      <div class="filter-section">
        <div class="filter-label">Location</div>
        <input class="filter-input" type="text" name="location" placeholder="City or state...">
      </div>

      <div class="filter-section">
        <div class="filter-label">Property Type</div>
        <select class="filter-input" name="type">
          <option value="">Any</option>
          <option value="house">House</option>
          <option value="apartment">Apartment</option>
          <option value="villa">Villa</option>
          <option value="commercial">Commercial</option>
          <option value="land">Land</option>
        </select>
      </div>

      <div class="filter-section">
        <div class="filter-label">Listing Type</div>
        <select class="filter-input" name="listing">
          <option value="">Rent or Sale</option>
          <option value="rent">For Rent</option>
          <option value="sale">For Sale</option>
        </select>
      </div>

      <div class="filter-section">
        <div class="filter-label">Price Range ($)</div>
        <div class="price-range-row">
          <input class="filter-input" type="number" name="minPrice" placeholder="Min">
          <input class="filter-input" type="number" name="maxPrice" placeholder="Max">
        </div>
      </div>

      <div class="filter-section">
        <div class="filter-label">Bedrooms</div>
        <select class="filter-input" name="beds">
          <option value="">Any</option>
          <option value="1">1+</option>
          <option value="2">2+</option>
          <option value="3">3+</option>
          <option value="4">4+</option>
        </select>
      </div>

      <div class="filter-section">
        <div class="filter-label">Status</div>
        <div class="chip-group">
          <button type="button" class="chip active" data-val="available">Available</button>
          <button type="button" class="chip" data-val="rented">Rented</button>
          <button type="button" class="chip" data-val="sold">Sold</button>
        </div>
        <!-- Hidden input carries selected status to servlet -->
        <input type="hidden" name="status" id="status-val" value="available">
      </div>

      <div style="margin-top: 24px; display: flex; flex-direction: column; gap: 8px;">
        <button type="submit" class="btn-primary" style="width:100%; padding:11px;">Apply Filters</button>
        <button type="reset"  class="btn-ghost"   style="width:100%; padding:10px;">Reset</button>
      </div>
    </aside>

    <!-- ── RESULTS ── -->
    <div class="results-area">
      <div class="results-topbar">
        <div class="results-count">Showing <strong>6 results</strong></div>
        <!--
          BACK-END NOTE:
          Replace "6 results" with:
          Showing <strong><%= results.size() %> results</strong>
        -->
        <select class="sort-select" name="sort">
          <option value="newest">Newest first</option>
          <option value="price_asc">Price: Low to High</option>
          <option value="price_desc">Price: High to Low</option>
        </select>
      </div>

      <!--
        BACK-END NOTE:
        JSP loop goes here — same card structure as properties.jsp
      -->
      <div class="prop-grid">

        <a href="property-detail.jsp?id=1" class="prop-card">
          <div class="prop-card-img">🏢<span class="listing-badge badge-rent">For Rent</span></div>
          <div class="prop-card-body">
            <div class="prop-card-name">The Ritz Residences</div>
            <div class="prop-card-loc"><svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>California</div>
            <div class="prop-card-footer"><div class="prop-price">$4,200/mo</div><div class="prop-tags"><span class="prop-tag">3 bed</span></div></div>
            <div class="prop-card-lister"><div class="lister-avatar">SK</div><span class="lister-name">Sarah K.</span></div>
          </div>
        </a>

        <a href="property-detail.jsp?id=2" class="prop-card">
          <div class="prop-card-img c2">🏡<span class="listing-badge badge-sale">For Sale</span></div>
          <div class="prop-card-body">
            <div class="prop-card-name">CityCenterDC Apartments</div>
            <div class="prop-card-loc"><svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>Illinois</div>
            <div class="prop-card-footer"><div class="prop-price">$320,000</div><div class="prop-tags"><span class="prop-tag">4 bed</span></div></div>
            <div class="prop-card-lister"><div class="lister-avatar">MT</div><span class="lister-name">Mark T.</span></div>
          </div>
        </a>

        <a href="property-detail.jsp?id=3" class="prop-card">
          <div class="prop-card-img c3">🏙️<span class="listing-badge badge-rent">For Rent</span></div>
          <div class="prop-card-body">
            <div class="prop-card-name">Skyline Tower Suite</div>
            <div class="prop-card-loc"><svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>New York</div>
            <div class="prop-card-footer"><div class="prop-price">$7,500/mo</div><div class="prop-tags"><span class="prop-tag">5 bed</span></div></div>
            <div class="prop-card-lister"><div class="lister-avatar">PM</div><span class="lister-name">Priya M.</span></div>
          </div>
        </a>

        <a href="property-detail.jsp?id=4" class="prop-card">
          <div class="prop-card-img c4">🏠<span class="listing-badge badge-sale">For Sale</span></div>
          <div class="prop-card-body">
            <div class="prop-card-name">Green Valley Villa</div>
            <div class="prop-card-loc"><svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>Texas</div>
            <div class="prop-card-footer"><div class="prop-price">$1,200,000</div><div class="prop-tags"><span class="prop-tag">6 bed</span></div></div>
            <div class="prop-card-lister"><div class="lister-avatar">CR</div><span class="lister-name">Carlos R.</span></div>
          </div>
        </a>

        <a href="property-detail.jsp?id=5" class="prop-card">
          <div class="prop-card-img">🏘️<span class="listing-badge badge-rent">For Rent</span></div>
          <div class="prop-card-body">
            <div class="prop-card-name">Harbor View Cottage</div>
            <div class="prop-card-loc"><svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>Florida</div>
            <div class="prop-card-footer"><div class="prop-price">$2,100/mo</div><div class="prop-tags"><span class="prop-tag">2 bed</span></div></div>
            <div class="prop-card-lister"><div class="lister-avatar">EL</div><span class="lister-name">Emma L.</span></div>
          </div>
        </a>

        <a href="property-detail.jsp?id=6" class="prop-card">
          <div class="prop-card-img c3">🏗️<span class="listing-badge badge-rent">For Rent</span></div>
          <div class="prop-card-body">
            <div class="prop-card-name">Midtown Office Suite</div>
            <div class="prop-card-loc"><svg viewBox="0 0 24 24"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0118 0z"/><circle cx="12" cy="10" r="3"/></svg>New York</div>
            <div class="prop-card-footer"><div class="prop-price">$5,800/mo</div><div class="prop-tags"><span class="prop-tag">Commercial</span></div></div>
            <div class="prop-card-lister"><div class="lister-avatar">DC</div><span class="lister-name">David C.</span></div>
          </div>
        </a>

      </div>
    </div>
  </form>

  <script>
    /* Status chip toggle */
    document.querySelectorAll('.filter-panel .chip').forEach(chip => {
      chip.addEventListener('click', function () {
        document.querySelectorAll('.filter-panel .chip').forEach(c => c.classList.remove('active'));
        this.classList.add('active');
        document.getElementById('status-val').value = this.dataset.val;
      });
    });
  </script>

</body>
</html>

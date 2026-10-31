<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>FarmEase | Farm Equipment Rental Marketplace</title>

  <meta name="description"
        content="Rent tractors, harvesters, and essential agricultural equipment at affordable prices with FarmEase.">

  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/tailwind.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&family=Manrope:wght@400;500;600;700;800&display=swap"
        rel="stylesheet">

</head>

<body class="bg-white text-gray-800">

<!-- ================= NAVBAR ================= -->

<header class="navbar">
  <nav class="container mx-auto flex items-center justify-between px-5 py-4">
    <a href="${pageContext.request.contextPath}/index.jsp"
       class="logo flex items-center gap-2">

      <span class="text-3xl">🌾</span>

      <span class="text-2xl font-extrabold text-farm-800">
            Farm<span class="text-farm-500">Ease</span>
        </span>
    </a>

    <div class="hidden items-center gap-8 md:flex">
      <a href="#home" class="nav-link">Home</a>
      <a href="#equipment" class="nav-link">Equipment</a>
      <a href="#benefits" class="nav-link">Why FarmEase?</a>
      <a href="#about" class="nav-link">About Us</a>
    </div>

    <div class="flex items-center gap-3">

      <a href="<%= request.getContextPath() %>/authentication/login.jsp"
         class="btn-outline">
        Login
      </a>

      <a href="<%= request.getContextPath() %>/authentication/register.jsp"
         class="btn-primary">
        Get Started
      </a>

    </div>
  </nav>


</header>

<main>

  <!-- ================= HERO SECTION ================= -->

  <section id="home" class="hero-section">

    <div class="container mx-auto grid items-center gap-12 px-5 py-16 lg:grid-cols-2 lg:py-24">

      <div class="reveal">

            <span class="hero-badge">
                🌱 Smart Farming Starts Here
            </span>

        <h1 class="mt-6 text-4xl font-extrabold leading-tight text-gray-900 md:text-6xl">
          Rent the Right Equipment.
          <span class="text-farm-700">
                    Grow More.
                </span>
        </h1>

        <p class="mt-6 max-w-xl text-lg leading-8 text-gray-600">
          Access reliable agricultural equipment without the
          high cost of ownership. Find tractors, harvesters,
          and farming tools whenever you need them.
        </p>

        <div class="mt-8 flex flex-wrap gap-4">

          <a href="<%= request.getContextPath() %>/authentication/register.jsp"
             class="btn-primary btn-large">
            Start Renting
            <span aria-hidden="true">→</span>
          </a>

          <a href="#equipment"
             class="btn-outline btn-large">
            Explore Equipment
          </a>

        </div>

        <div class="mt-10 grid max-w-lg grid-cols-3 gap-5">

          <div>
            <h3 class="text-2xl font-extrabold text-farm-800">
              Affordable
            </h3>
            <p class="mt-1 text-sm text-gray-500">
              Rental options
            </p>
          </div>

          <div>
            <h3 class="text-2xl font-extrabold text-farm-800">
              Flexible
            </h3>
            <p class="mt-1 text-sm text-gray-500">
              Rental periods
            </p>
          </div>

          <div>
            <h3 class="text-2xl font-extrabold text-farm-800">
              Convenient
            </h3>
            <p class="mt-1 text-sm text-gray-500">
              Equipment access
            </p>
          </div>

        </div>

      </div>


      <div class="hero-image-wrapper reveal">

        <img
                src="https://images.unsplash.com/photo-1500382017468-9049fed747ef?auto=format&fit=crop&w=1200&q=85"
                alt="Green agricultural fields ready for farming"
                class="hero-image"
        >

        <div class="floating-card floating-card-top">
          <span class="text-2xl">🚜</span>
          <div>
            <p class="font-bold text-gray-800">
              Farm Smarter
            </p>
            <p class="text-sm text-gray-500">
              Equipment when you need it
            </p>
          </div>
        </div>

        <div class="floating-card floating-card-bottom">
          <span class="text-2xl">🌿</span>
          <div>
            <p class="font-bold text-gray-800">
              Grow Efficiently
            </p>
            <p class="text-sm text-gray-500">
              Reduce equipment costs
            </p>
          </div>
        </div>

      </div>

    </div>

  </section>


  <!-- ================= EQUIPMENT SECTION ================= -->

  <section id="equipment" class="py-20">

    <div class="container mx-auto px-5">

      <!-- Section Heading -->
      <div class="section-heading reveal">

            <span class="section-label">
                OUR EQUIPMENT
            </span>

        <h2 class="section-title">
          Everything Your <span class="text-gradient">Farm Needs</span>
        </h2>

        <p class="section-description">
          Explore essential farming equipment to make your
          agricultural work easier and more productive.
        </p>

      </div>

      <!-- Equipment Cards -->
      <div class="mt-12 grid gap-8 sm:grid-cols-2 lg:grid-cols-3">

        <!-- Tractor Card -->
        <article class="equipment-card reveal">

          <div class="equipment-image-wrapper">

            <img
                    src="https://images.unsplash.com/photo-1530267981375-f0de937f5f13?auto=format&fit=crop&w=900&q=85"
                    alt="Tractor working on a farm"
                    class="equipment-image"
                    loading="lazy"
                    onerror="this.onerror=null;this.src='https://placehold.co/900x500/e8f5e9/166534?text=Farm+Tractor';"
            >

            <span class="equipment-tag">Popular</span>

          </div>

          <div class="equipment-content">

                    <span class="equipment-category">
                        LAND PREPARATION
                    </span>

            <h3 class="equipment-title">Tractors</h3>

            <p class="equipment-description">
              Prepare your land, plough fields, and complete
              demanding farming tasks efficiently.
            </p>

            <a href="${pageContext.request.contextPath}/authentication/register.jsp"
               class="equipment-link">
              Find a Tractor <span aria-hidden="true">→</span>
            </a>

          </div>

        </article>


        <!-- Harvester Card -->
        <article class="equipment-card reveal">

          <div class="equipment-image-wrapper">

            <img
                    src="https://images.unsplash.com/photo-1500382017468-9049fed747ef?auto=format&fit=crop&w=900&q=85"
                    alt="Golden agricultural fields"
                    class="equipment-image"
                    loading="lazy"
                    onerror="this.onerror=null;this.src='https://placehold.co/900x500/e8f5e9/166534?text=Harvesting+Equipment';"
            >

            <span class="equipment-tag">Efficient</span>

          </div>

          <div class="equipment-content">

                    <span class="equipment-category">
                        CROP HARVESTING
                    </span>

            <h3 class="equipment-title">Harvesters</h3>

            <p class="equipment-description">
              Save time during harvest season with suitable
              agricultural harvesting equipment.
            </p>

            <a href="${pageContext.request.contextPath}/authentication/register.jsp"
               class="equipment-link">
              Explore Harvesters <span aria-hidden="true">→</span>
            </a>

          </div>

        </article>


        <!-- Farming Tools Card -->
        <article class="equipment-card reveal">

          <div class="equipment-image-wrapper">

            <img
                    src="https://images.unsplash.com/photo-1416879595882-3373a0480b5b?auto=format&fit=crop&w=900&q=85"
                    alt="Gardening and farming tools"
                    class="equipment-image"
                    loading="eager"
                    onerror="this.onerror=null;this.src='https://placehold.co/900x500/dcfce7/166534?text=Farming+Tools';"
            >

            <span class="equipment-tag">Essential</span>

          </div>

          <div class="equipment-content">

                    <span class="equipment-category">
                        FARMING TOOLS
                    </span>

            <h3 class="equipment-title">Farming Equipment</h3>

            <p class="equipment-description">
              Find useful equipment and tools for everyday
              agricultural activities.
            </p>

            <a href="${pageContext.request.contextPath}/authentication/register.jsp"
               class="equipment-link">
              Explore Equipment <span aria-hidden="true">→</span>
            </a>

          </div>

        </article>

      </div>

    </div>

  </section>


  <!-- ================= BENEFITS SECTION ================= -->

  <section id="benefits" class="benefits-section py-20">

    <div class="container mx-auto px-5">

      <div class="section-heading reveal">

            <span class="section-label">
                WHY FARMEASE
            </span>

        <h2 class="section-title">
          A Better Way to Farm
        </h2>

        <p class="section-description">
          Make agricultural equipment more accessible
          with a convenient rental experience.
        </p>

      </div>


      <div class="mt-12 grid gap-8 md:grid-cols-3">

        <article class="feature-card reveal">

          <div class="feature-icon">💰</div>

          <h3 class="feature-title">
            Save Money
          </h3>

          <p class="feature-description">
            Rent equipment when needed instead of making
            a large investment in purchasing machinery.
          </p>

        </article>


        <article class="feature-card reveal">

          <div class="feature-icon">⏱️</div>

          <h3 class="feature-title">
            Save Time
          </h3>

          <p class="feature-description">
            Find suitable equipment for your farming
            activities through one convenient platform.
          </p>

        </article>


        <article class="feature-card reveal">

          <div class="feature-icon">🤝</div>

          <h3 class="feature-title">
            Connect Farmers
          </h3>

          <p class="feature-description">
            Help farmers access equipment and allow
            equipment owners to offer their machinery for rent.
          </p>

        </article>

      </div>

    </div>

  </section>


  <!-- ================= ABOUT SECTION ================= -->

  <section id="about" class="py-20">

    <div class="container mx-auto grid items-center gap-12 px-5 lg:grid-cols-2">

      <div class="reveal">

        <img
                src="https://images.unsplash.com/photo-1499529112087-3cb3b73cec95?auto=format&fit=crop&w=1200&q=85"
                alt="Agricultural landscape"
                class="about-image"
                loading="lazy"
        >

      </div>


      <div class="reveal">

            <span class="section-label">
                ABOUT FARMEASE
            </span>

        <h2 class="mt-4 text-3xl font-extrabold leading-tight text-gray-900 md:text-4xl">
          Making Modern Farming More Accessible
        </h2>

        <p class="mt-5 leading-8 text-gray-600">
          FarmEase is designed to connect farmers with
          agricultural equipment owners through a simple
          rental marketplace.
        </p>

        <p class="mt-4 leading-8 text-gray-600">
          Our goal is to make equipment rental convenient,
          support better resource utilization, and help
          farmers manage equipment costs.
        </p>

        <a href="<%= request.getContextPath() %>/authentication/register.jsp"
           class="btn-primary mt-7 inline-flex">
          Join FarmEase <span class="ml-2" aria-hidden="true">→</span>
        </a>

      </div>

    </div>

  </section>


  <!-- ================= CTA SECTION ================= -->

  <section class="cta-section">

    <div class="container mx-auto px-5 py-16 text-center reveal">

      <span class="text-4xl">🌾</span>

      <h2 class="mt-5 text-3xl font-extrabold md:text-4xl">
        Ready to Make Farming Easier?
      </h2>

      <p class="mx-auto mt-4 max-w-2xl leading-7">
        Create your account and explore agricultural equipment
        rental opportunities with FarmEase.
      </p>

      <a href="<%= request.getContextPath() %>/authentication/register.jsp"
         class="btn-light mt-8 inline-flex">
        Create Your Account <span class="ml-2" aria-hidden="true">→</span>
      </a>

    </div>

  </section>


</main>

<!-- ================= FOOTER ================= -->

<footer class="footer">

  <div class="container mx-auto grid gap-10 px-5 py-12 md:grid-cols-3">

    <div>

      <a href="${pageContext.request.contextPath}/authentication/index.jsp"
         class="logo inline-flex items-center gap-2">

        <span class="text-3xl">🌾</span>

        <span class="text-2xl font-extrabold text-white">
                FarmEase
            </span>

      </a>

      <p class="mt-4 max-w-sm leading-7 text-gray-300">
        Connecting farmers with the equipment they need
        to make farming more accessible and efficient.
      </p>

    </div>


    <div>

      <h3 class="footer-heading">
        Quick Links
      </h3>

      <div class="mt-4 flex flex-col gap-3">

        <a href="#home" class="footer-link">Home</a>
        <a href="#equipment" class="footer-link">Equipment</a>
        <a href="#benefits" class="footer-link">Benefits</a>
        <a href="#about" class="footer-link">About Us</a>

      </div>

    </div>


    <div>

      <h3 class="footer-heading">
        Your Account
      </h3>

      <div class="mt-4 flex flex-col gap-3">

        <a href="<%= request.getContextPath() %>/authentication/login.jsp"
           class="footer-link">
          Login
        </a>

        <a href="<%= request.getContextPath() %>/authentication/register.jsp"
           class="footer-link">
          Create Account
        </a>

      </div>

    </div>

  </div>


  <div class="footer-bottom">

    <p>
      &copy; <span id="currentYear">2026</span> FarmEase.
      All rights reserved.
    </p>

    <p>
      Built for a better farming future 🌱
    </p>

  </div>

</footer>

<!-- External JavaScript -->

<script src="<%= request.getContextPath() %>/js/auth.js" defer></script>
</body>
</html>

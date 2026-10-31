<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Login | FarmEase</title>

  <link rel="stylesheet"
        href="<%= request.getContextPath() %>/css/tailwind.css">

  <link rel="stylesheet"
        href="<%= request.getContextPath() %>/css/style.css">
</head>

<body class="login-page">

<header class="bg-white shadow-sm">
  <nav class="max-w-7xl mx-auto px-6 py-5 flex items-center justify-between">

    <a href="<%= request.getContextPath() %>/index.jsp"
       class="text-2xl font-bold text-green-800">
      🌾FarmEase
    </a>



  </nav>
</header>

<main class="login-wrapper">

  <div class="login-card">

    <!-- Left Section -->
    <section class="login-info">

      <h1>Welcome Back to FarmEase!</h1>

      <p>
        Your farming journey starts here. Log in to find farming
        equipment, manage your listings and make farming easier.
      </p>

      <div class="login-benefits">
        <p>✓ Easy equipment rental</p>
        <p>✓ Manage your farming activities</p>
        <p>✓ One platform for farmers and owners</p>
      </div>

    </section>

    <!-- Right Section -->
    <section class="login-form">

      <h2 id="formTitle">Sign In</h2>

      <p class="login-subtitle" id="formSubtitle">
        Enter your account credentials to continue.
      </p>

      <%
        String error = request.getParameter("error");
        String registered = request.getParameter("registered");
        String logout = request.getParameter("logout");
        String otpSent = request.getParameter("otpSent");
        String otpError = request.getParameter("otpError");

        if ("true".equals(error)) {
      %>

      <div class="login-message error-message">
        Invalid email or password. Please try again.
      </div>

      <%
      } else if ("unauthorized".equals(error)) {
      %>

      <div class="login-message error-message">
        You do not have permission to access that page.
      </div>

      <%
      } else if ("true".equals(registered)) {
      %>

      <div class="login-message success-message">
        Registration successful! Please log in.
      </div>

      <%
      } else if ("true".equals(logout)) {
      %>

      <div class="login-message success-message">
        You have been logged out successfully.
      </div>

      <%
        }

        if ("true".equals(otpSent)) {
      %>

      <div class="login-message success-message">
        OTP sent to your registered email address.
      </div>

      <%
        }

        if ("true".equals(otpError)) {
      %>

      <div class="login-message error-message">
        Invalid or expired OTP. Please try again.
      </div>

      <%
        }
      %>

      <!-- STEP 1: EMAIL AND PASSWORD -->
      <form id="loginForm"
            action="<%= request.getContextPath() %>/login"
            method="post">

        <div class="form-group">

          <label for="email">Email Address</label>

          <input
                  type="email"
                  id="email"
                  name="email"
                  placeholder="Enter your email"
                  autocomplete="email"
                  required>

        </div>

        <div class="form-group">

          <label for="password">Password</label>

          <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="Enter your password"
                  autocomplete="current-password"
                  required>

        </div>

        <button type="submit" class="login-button">
          Log in
        </button>

      </form>

      <!-- STEP 2: OTP VERIFICATION -->
      <form id="otpForm"
            action="<%= request.getContextPath() %>/verify-otp"
            method="post"
            style="display: none;">

        <div class="form-group">

          <label for="otp">Enter Email OTP</label>

          <input
                  type="text"
                  id="otp"
                  name="otp"
                  placeholder="Enter 6-digit OTP"
                  inputmode="numeric"
                  pattern="[0-9]{6}"
                  maxlength="6"
                  autocomplete="one-time-code"
                  required>

        </div>

        <button type="submit" class="login-button">
          Verify OTP & Login
        </button>

        <p style="text-align: center; margin-top: 15px;">
          OTP is valid for 5 minutes.
        </p>

        <button type="button"
                id="backButton"
                class="login-button"
                style="margin-top: 10px; background-color: #64748b;">
          Back to Login
        </button>

      </form>

      <div class="register-link" id="registerLink">
        Don't have an account?

        <a href="<%= request.getContextPath() %>/authentication/register.jsp">
          Register Now
        </a>
      </div>

    </section>

  </div>

</main>

<script>
  const loginForm = document.getElementById("loginForm");
  const otpForm = document.getElementById("otpForm");
  const formTitle = document.getElementById("formTitle");
  const formSubtitle = document.getElementById("formSubtitle");
  const registerLink = document.getElementById("registerLink");
  const backButton = document.getElementById("backButton");

  function showOtpForm() {
    loginForm.style.display = "none";
    otpForm.style.display = "block";

    formTitle.textContent = "Verify Your Email";

    formSubtitle.textContent =
            "Enter the 6-digit OTP sent to your registered email.";

    registerLink.style.display = "none";
  }

  function showLoginForm() {
    otpForm.style.display = "none";
    loginForm.style.display = "block";

    formTitle.textContent = "Sign In";

    formSubtitle.textContent =
            "Enter your account credentials to continue.";

    registerLink.style.display = "block";
  }

  backButton.addEventListener("click", showLoginForm);

  /*
   * Show OTP form only when the backend confirms
   * that an OTP has been sent.
   */
  const otpSent = <%= "true".equals(otpSent) %>;

  if (otpSent) {
    showOtpForm();
  }
</script>

</body>
</html>
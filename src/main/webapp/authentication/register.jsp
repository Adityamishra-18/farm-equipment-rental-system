<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11-10-2026
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Create Account | FarmEase</title>

  <link rel="stylesheet"
        href="<%= request.getContextPath() %>/css/tailwind.css">

  <link rel="stylesheet"
        href="<%= request.getContextPath() %>/css/style.css">

  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&family=Manrope:wght@400;500;600;700;800&display=swap"
        rel="stylesheet">
</head>

<body class="bg-green-50 text-gray-800">

<!-- ================= NAVBAR ================= -->

<header class="navbar bg-white">

  <nav class="container mx-auto flex items-center justify-between px-5 py-4">

    <a href="<%= request.getContextPath() %>/index.jsp"
       class="logo flex items-center gap-2">

      <span class="text-3xl">🌾</span>

      <span class="text-2xl font-extrabold text-farm-800">
                Farm<span class="text-farm-500">Ease</span>
            </span>

    </a>

    <a href="<%= request.getContextPath() %>/authentication/login.jsp"
       class="btn-outline">
      Login
    </a>

  </nav>

</header>


<!-- ================= REGISTER SECTION ================= -->

<main class="min-h-screen px-5 py-12">

  <div class="mx-auto grid max-w-5xl items-center gap-12 lg:grid-cols-2">

    <!-- LEFT SIDE CONTENT -->

    <section class="hidden lg:block">

            <span class="hero-badge">
                🌱 Welcome to FarmEase
            </span>

      <h1 class="mt-6 text-5xl font-extrabold leading-tight text-gray-900">
        Grow Your Farming
        <span class="text-farm-700">
                    Journey With Us.
                </span>
      </h1>

      <p class="mt-6 text-lg leading-8 text-gray-600">
        Join FarmEase to explore agricultural equipment rental
        opportunities and make farming more convenient and affordable.
      </p>

      <div class="mt-8 space-y-5">

        <div class="flex items-center gap-3">
          <span class="text-2xl">🚜</span>
          <p class="font-semibold text-gray-700">
            Access essential farming equipment
          </p>
        </div>

        <div class="flex items-center gap-3">
          <span class="text-2xl">💰</span>
          <p class="font-semibold text-gray-700">
            Reduce equipment ownership costs
          </p>
        </div>

        <div class="flex items-center gap-3">
          <span class="text-2xl">🤝</span>
          <p class="font-semibold text-gray-700">
            Connect farmers and equipment owners
          </p>
        </div>

      </div>

    </section>


    <!-- REGISTRATION FORM -->

    <section class="rounded-3xl border border-green-100 bg-white p-6 shadow-xl sm:p-10">

      <div class="mb-8 text-center">

        <div class="mb-4 text-5xl">🌾</div>

        <h2 class="text-3xl font-extrabold text-gray-900">
          Create Your Account
        </h2>

        <p class="mt-3 text-gray-500">
          Join FarmEase and get started today.
        </p>

      </div>


      <!-- ERROR MESSAGES -->

      <%
        String error = request.getParameter("error");
        String registered = request.getParameter("registered");

        if ("true".equals(error)) {
      %>

      <div class="mb-5 rounded-xl border border-red-200 bg-red-50 p-3 text-sm text-red-700">
        Registration failed. Please check your details and try again.
      </div>

      <%
        }

        if ("exists".equals(error)) {
      %>

      <div class="mb-5 rounded-xl border border-red-200 bg-red-50 p-3 text-sm text-red-700">
        This email is already registered. Please log in instead.
      </div>

      <%
        }

        if ("true".equals(registered)) {
      %>

      <div class="mb-5 rounded-xl border border-green-200 bg-green-50 p-3 text-sm text-green-700">
        Registration successful! You can now log in.
      </div>

      <%
        }
      %>


      <!-- FORM -->

      <form action="<%= request.getContextPath() %>/register"
            method="post"
            id="registerForm"
            class="space-y-5">

        <!-- FULL NAME -->

        <div>
          <label for="name"
                 class="mb-2 block text-sm font-semibold text-gray-700">
            Full Name
          </label>

          <input
                  type="text"
                  id="name"
                  name="name"
                  placeholder="Enter your full name"
                  required
                  minlength="2"
                  maxlength="100"
                  autocomplete="name"
                  class="w-full rounded-xl border border-gray-300 px-4 py-3 outline-none transition focus:border-green-600 focus:ring-2 focus:ring-green-100"
          >
        </div>


        <!-- EMAIL -->

        <div>
          <label for="email"
                 class="mb-2 block text-sm font-semibold text-gray-700">
            Email Address
          </label>

          <input
                  type="email"
                  id="email"
                  name="email"
                  placeholder="you@example.com"
                  required
                  maxlength="150"
                  autocomplete="email"
                  class="w-full rounded-xl border border-gray-300 px-4 py-3 outline-none transition focus:border-green-600 focus:ring-2 focus:ring-green-100"
          >
        </div>


        <!-- ROLE -->

        <div>
          <label for="role"
                 class="mb-2 block text-sm font-semibold text-gray-700">
            Select Account Type
          </label>

          <select
                  id="role"
                  name="role"
                  required
                  class="w-full rounded-xl border border-gray-300 bg-white px-4 py-3 outline-none transition focus:border-green-600 focus:ring-2 focus:ring-green-100">

            <option value="FARMER" selected>
              Farmer — Rent Equipment
            </option>

            <option value="OWNER">
              Equipment Owner — Offer Equipment
            </option>

          </select>
        </div>


        <!-- PASSWORD -->

        <div>
          <label for="password"
                 class="mb-2 block text-sm font-semibold text-gray-700">
            Password
          </label>

          <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="Create a password"
                  required
                  minlength="8"
                  maxlength="72"
                  autocomplete="new-password"
                  class="w-full rounded-xl border border-gray-300 px-4 py-3 outline-none transition focus:border-green-600 focus:ring-2 focus:ring-green-100"
          >

          <p class="mt-2 text-xs text-gray-500">
            Password must contain at least 8 characters.
          </p>
        </div>


        <!-- CONFIRM PASSWORD -->

        <div>
          <label for="confirmPassword"
                 class="mb-2 block text-sm font-semibold text-gray-700">
            Confirm Password
          </label>

          <input
                  type="password"
                  id="confirmPassword"
                  name="confirmPassword"
                  placeholder="Re-enter your password"
                  required
                  minlength="8"
                  maxlength="72"
                  autocomplete="new-password"
                  class="w-full rounded-xl border border-gray-300 px-4 py-3 outline-none transition focus:border-green-600 focus:ring-2 focus:ring-green-100"
          >

          <p id="passwordError"
             class="mt-2 hidden text-sm text-red-600">
            Passwords do not match.
          </p>
        </div>


        <!-- TERMS AND CONDITIONS -->

        <div class="flex items-start gap-3">

          <input
                  type="checkbox"
                  id="terms"
                  name="terms"
                  required
                  class="mt-1 h-4 w-4 accent-green-700"
          >

          <label for="terms"
                 class="text-sm leading-6 text-gray-600">
            I agree to provide accurate registration information
            and use FarmEase responsibly.
          </label>

        </div>


        <!-- SUBMIT BUTTON -->

        <button
                type="submit"
                class="w-full rounded-xl bg-green-700 px-5 py-3.5 font-bold text-white shadow-md transition hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-200">

          Create Account
          <span aria-hidden="true">→</span>

        </button>

      </form>


      <!-- LOGIN LINK -->

      <p class="mt-7 text-center text-sm text-gray-600">

        Already have an account?

        <a href="<%= request.getContextPath() %>/authentication/login.jsp"
           class="font-bold text-green-700 hover:text-green-900">
          Log in
        </a>

      </p>

    </section>

  </div>

</main>


<!-- ================= FOOTER ================= -->

<footer class="border-t border-green-100 bg-white px-5 py-6 text-center">

  <p class="text-sm text-gray-500">
    &copy; 2026 FarmEase. All rights reserved.
  </p>

</footer>


<!-- ================= PASSWORD VALIDATION ================= -->

<script>
  const registerForm = document.getElementById("registerForm");
  const passwordInput = document.getElementById("password");
  const confirmPasswordInput = document.getElementById("confirmPassword");
  const passwordError = document.getElementById("passwordError");

  function validatePasswords() {

    if (confirmPasswordInput.value === "") {
      passwordError.classList.add("hidden");
      confirmPasswordInput.setCustomValidity("");
      return true;
    }

    const matches =
            passwordInput.value === confirmPasswordInput.value;

    passwordError.classList.toggle("hidden", matches);

    confirmPasswordInput.setCustomValidity(
            matches ? "" : "Passwords do not match."
    );

    return matches;
  }

  passwordInput.addEventListener("input", validatePasswords);
  confirmPasswordInput.addEventListener("input", validatePasswords);

  registerForm.addEventListener("submit", function (event) {

    if (!validatePasswords()) {
      event.preventDefault();
      confirmPasswordInput.reportValidity();
    }

  });
</script>

</body>
</html>

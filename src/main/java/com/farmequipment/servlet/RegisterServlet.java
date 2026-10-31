package com.farmequipment.servlet;

import com.farmequipment.dao.UserDAO;
import com.farmequipment.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        response.sendRedirect(
                request.getContextPath() + "/authentication/register.jsp"
        );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = trim(request.getParameter("name"));
        String email = trim(request.getParameter("email")).toLowerCase();

        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        String role = request.getParameter("role");

        // Form values preserve karna
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("role", role);

        String error = null;

        // Validation
        if (name.isBlank()
                || email.isBlank()
                || blank(password)
                || blank(confirmPassword)) {

            error = "All fields are required.";

        } else if (name.length() > 100) {

            error = "Name must be 100 characters or fewer.";

        } else if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            error = "Please enter a valid email address.";

        } else if (password.length() < 6) {

            error = "Password must contain at least 6 characters.";

        } else if (!password.equals(confirmPassword)) {

            error = "Passwords do not match.";

        } else if (!"FARMER".equals(role)
                && !"OWNER".equals(role)) {

            error = "Please select a valid account type.";

        } else if (userDAO.findByEmail(email) != null) {

            error = "This email is already registered. Please log in.";
        }

        // Validation fail hone par registration page par wapas
        if (error != null) {

            request.setAttribute("error", error);

            request.getRequestDispatcher(
                    "/authentication/register.jsp"
            ).forward(request, response);

            return;
        }

        // User object create karna
        User user = new User(name, email, password);

        user.setRole(role);
        user.setStatus("ACTIVE");

        // Database mein registration
        if (userDAO.registerUser(user)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/authentication/login.jsp?registered=1"
            );

        } else {

            request.setAttribute(
                    "error",
                    "Registration failed. Check the database connection and try again."
            );

            request.getRequestDispatcher(
                    "/authentication/register.jsp"
            ).forward(request, response);
        }
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private boolean blank(String value) {
        return value == null || value.isBlank();
    }
}
package com.farmequipment.servlet;

import com.farmequipment.dao.UserDAO;
import com.farmequipment.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || password == null
                || email.isBlank() || password.isBlank()) {

            request.setAttribute(
                    "error",
                    "Email and password are required."
            );

            request.getRequestDispatcher(
                    "/authentication/login.jsp"
            ).forward(request, response);

            return;
        }

        email = email.trim().toLowerCase();

        // Find user by email
        User user = userDAO.findByEmail(email);

        // Validate credentials and account status
        if (user == null
                || user.getPassword() == null
                || !user.getPassword().equals(password)
                || !"ACTIVE".equalsIgnoreCase(user.getStatus())) {

            request.setAttribute(
                    "error",
                    "Invalid email or password, or account is inactive."
            );

            request.getRequestDispatcher(
                    "/authentication/login.jsp"
            ).forward(request, response);

            return;
        }

        // Get role from database
        String role = user.getRole();

        // Invalidate previous session
        HttpSession oldSession = request.getSession(false);

        if (oldSession != null) {
            oldSession.invalidate();
        }

        // Create new session
        HttpSession session = request.getSession(true);

        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getName());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userRole", role);

        // Redirect according to role
        if ("ADMIN".equalsIgnoreCase(role)) {

            // Required by AdminAuthFilter
            session.setAttribute("admin", user);

            response.sendRedirect(
                    request.getContextPath() + "/admin/dashboard"
            );

        } else if ("OWNER".equalsIgnoreCase(role)) {

            response.sendRedirect(
                    request.getContextPath() + "/equipmentList"

            );

        } else if ("FARMER".equalsIgnoreCase(role)) {

            response.sendRedirect(
                    request.getContextPath() + "/farmer/farmer-home.jsp"
            );

        } else {

            // Invalid or unsupported role
            session.invalidate();

            request.setAttribute(
                    "error",
                    "Your account has an invalid role. Please contact the administrator."
            );

            request.getRequestDispatcher(
                    "/authentication/login.jsp"
            ).forward(request, response);
        }
    }
}
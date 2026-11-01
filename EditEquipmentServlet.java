package com.farmequipment.servlet;

import com.farmequipment.dao.EquipmentDAO;
import com.farmequipment.model.Equipment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/editEquipment")
public class EditEquipmentServlet extends HttpServlet {


    private EquipmentDAO equipmentDAO;

    @Override
    public void init() {
        equipmentDAO = new EquipmentDAO();
    }

    // Display existing equipment details
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Object userIdObject = session.getAttribute("userId");
        Object roleObject = session.getAttribute("userRole");

        if (!(userIdObject instanceof Number) ||
                roleObject == null ||
                !"OWNER".equalsIgnoreCase(roleObject.toString())) {
            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Only equipment owners can edit equipment"
            );
            return;
        }

        int ownerId = ((Number) userIdObject).intValue();

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Equipment equipment = equipmentDAO.getEquipmentById(id);

            if (equipment == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Equipment not found"
                );
                return;
            }

            // Verify equipment belongs to the logged-in owner
            if (equipment.getOwnerId() == null ||
                    equipment.getOwnerId() != ownerId) {
                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "You cannot edit another owner's equipment"
                );
                return;
            }

            request.setAttribute("equipment", equipment);

            request.getRequestDispatcher(
                    "/equipment/edit-equipment.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid equipment ID"
            );
        }
    }

    // Save updated equipment details
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Object userIdObject = session.getAttribute("userId");
        Object roleObject = session.getAttribute("userRole");

        if (!(userIdObject instanceof Number) ||
                roleObject == null ||
                !"OWNER".equalsIgnoreCase(roleObject.toString())) {
            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Only equipment owners can update equipment"
            );
            return;
        }

        int ownerId = ((Number) userIdObject).intValue();

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Equipment existing = equipmentDAO.getEquipmentById(id);

            if (existing == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Equipment not found"
                );
                return;
            }

            // Verify ownership before updating
            if (existing.getOwnerId() == null ||
                    existing.getOwnerId() != ownerId) {
                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "You cannot update another owner's equipment"
                );
                return;
            }

            existing.setName(request.getParameter("name"));
            existing.setCategory(request.getParameter("category"));
            existing.setBrand(request.getParameter("brand"));
            existing.setModel(request.getParameter("model"));
            existing.setDescription(request.getParameter("description"));

            double price = Double.parseDouble(
                    request.getParameter("pricePerDay")
            );

            if (price <= 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Price must be greater than zero"
                );
                return;
            }

            existing.setPricePerDay(price);
            existing.setLocation(request.getParameter("location"));
            existing.setImage(request.getParameter("image"));
            existing.setAvailability(request.getParameter("availability"));

            boolean updated = equipmentDAO.updateEquipment(existing, ownerId);

            if (updated) {
                response.sendRedirect(
                        request.getContextPath() + "/equipmentList"
                );
            } else {
                response.sendError(
                        HttpServletResponse.SC_CONFLICT,
                        "Equipment could not be updated"
                );
            }

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid equipment ID or price"
            );
        }
    }

}

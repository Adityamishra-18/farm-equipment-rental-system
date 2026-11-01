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

@WebServlet("/deleteEquipment")
public class DeleteEquipmentServlet extends HttpServlet {


    private final EquipmentDAO equipmentDAO = new EquipmentDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check login
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Object userIdObject = session.getAttribute("userId");
        Object roleObject = session.getAttribute("userRole");

        // Only OWNER can delete equipment
        if (!(userIdObject instanceof Number) ||
                roleObject == null ||
                !"OWNER".equalsIgnoreCase(roleObject.toString())) {

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Only equipment owners can delete equipment"
            );
            return;
        }

        int ownerId = ((Number) userIdObject).intValue();

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            if (id <= 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid equipment ID"
                );
                return;
            }

            Equipment equipment = equipmentDAO.getEquipmentById(id);

            if (equipment == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Equipment not found"
                );
                return;
            }

            // Verify that this equipment belongs to the logged-in owner
            if (equipment.getOwnerId() == null ||
                    equipment.getOwnerId() != ownerId) {

                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "You cannot delete another owner's equipment"
                );
                return;
            }

            // DAO also verifies ownership before deleting
            boolean deleted = equipmentDAO.deleteEquipment(id, ownerId);

            if (deleted) {
                response.sendRedirect(
                        request.getContextPath() + "/equipmentList"
                );
            } else {
                response.sendError(
                        HttpServletResponse.SC_CONFLICT,
                        "Equipment could not be deleted"
                );
            }

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid equipment ID"
            );
        }
    }

}

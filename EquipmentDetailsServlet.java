
package com.farmequipment.servlet;

import com.farmequipment.dao.EquipmentDAO;
import com.farmequipment.model.Equipment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/equipmentDetails")
public class EquipmentDetailsServlet extends HttpServlet {

    private EquipmentDAO equipmentDAO;

    @Override
    public void init() {
        equipmentDAO = new EquipmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idParameter = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParameter);

            Equipment equipment = equipmentDAO.getEquipmentById(id);

            if (equipment == null) {
                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Equipment not found"
                );
                return;
            }

            request.setAttribute("equipment", equipment);

            request.getRequestDispatcher(
                    "/equipment/equipment-details.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid equipment ID"
            );
        }
    }
}


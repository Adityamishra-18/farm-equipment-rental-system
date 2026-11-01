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
import java.util.List;

@WebServlet("/equipmentList")
public class EquipmentListServlet extends HttpServlet {


    private EquipmentDAO equipmentDAO;

    @Override
    public void init() {
        equipmentDAO = new EquipmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Object roleObject = session != null
                ? session.getAttribute("userRole")
                : null;

        Object userIdObject = session != null
                ? session.getAttribute("userId")
                : null;

        List<Equipment> equipmentList;

        if (roleObject != null &&
                "OWNER".equalsIgnoreCase(roleObject.toString())) {

            // Owner sees only their own equipment
            if (!(userIdObject instanceof Number)) {
                response.sendRedirect(
                        request.getContextPath() + "/login.jsp"
                );
                return;
            }

            int ownerId = ((Number) userIdObject).intValue();

            equipmentList = equipmentDAO.getEquipmentByOwnerId(ownerId);

        } else {
            // Farmers and public marketplace see all equipment
            equipmentList = equipmentDAO.getAllEquipment();
        }

        request.setAttribute("equipmentList", equipmentList);

        request.getRequestDispatcher(
                "/equipment/equipment-list.jsp"
        ).forward(request, response);
    }

}

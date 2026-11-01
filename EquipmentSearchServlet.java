
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

@WebServlet("/equipmentSearch")
public class EquipmentSearchServlet extends HttpServlet {

    private final EquipmentDAO equipmentDAO = new EquipmentDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("userRole") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        String keyword = request.getParameter("keyword");
        Object roleObject = session.getAttribute("userRole");

        String role = roleObject.toString();
        List<Equipment> equipmentList;

        if ("OWNER".equalsIgnoreCase(role)) {

            Object userIdObject = session.getAttribute("userId");

            if (!(userIdObject instanceof Number)) {
                response.sendRedirect(
                        request.getContextPath() + "/login.jsp"
                );
                return;
            }

            int ownerId = ((Number) userIdObject).intValue();

            equipmentList = equipmentDAO.searchEquipmentByOwnerId(
                    keyword, ownerId
            );

        } else {

            if (keyword == null || keyword.isBlank()) {
                equipmentList = equipmentDAO.getAllEquipment();
            } else {
                equipmentList = equipmentDAO.searchEquipment(keyword);
            }
        }

        request.setAttribute("equipmentList", equipmentList);
        request.setAttribute("keyword", keyword);
        request.setAttribute("role", role);

        request.getRequestDispatcher(
                "/equipment/equipment-list.jsp"
        ).forward(request, response);
    }
}

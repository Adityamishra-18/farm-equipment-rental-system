
package com.farmequipment.servlet;

import com.farmequipment.dao.EquipmentDAO;
import com.farmequipment.model.Equipment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.UUID;

@WebServlet("/addEquipment")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 7 * 1024 * 1024
)
public class AddEquipmentServlet extends HttpServlet {

    private EquipmentDAO equipmentDAO;

    @Override
    public void init() {
        equipmentDAO = new EquipmentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("userId") == null
                || session.getAttribute("userRole") == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        if (!"OWNER".equalsIgnoreCase(
                session.getAttribute("userRole").toString())) {
            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Only owners can add equipment."
            );
            return;
        }

        Object userIdObject = session.getAttribute("userId");

        if (!(userIdObject instanceof Number)) {
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid user session."
            );
            return;
        }

        int ownerId = ((Number) userIdObject).intValue();

        if (ownerId <= 0) {
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid owner ID."
            );
            return;
        }

        String savedImageName = null;

        try {
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String description = request.getParameter("description");
            String location = request.getParameter("location");
            String availability = request.getParameter("availability");

            String priceText = request.getParameter("pricePerDay");

            if (name == null || name.isBlank()
                    || category == null || category.isBlank()
                    || location == null || location.isBlank()
                    || priceText == null || priceText.isBlank()) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Please complete all required equipment fields."
                );
                return;
            }

            double pricePerDay = Double.parseDouble(priceText);

            if (!Double.isFinite(pricePerDay) || pricePerDay <= 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Price must be greater than zero."
                );
                return;
            }

            Part imagePart = request.getPart("equipmentImage");

            if (imagePart == null || imagePart.getSize() == 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Please choose an equipment photo."
                );
                return;
            }

            if (imagePart.getSize() > 5L * 1024 * 1024) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Photo must be 5 MB or smaller."
                );
                return;
            }

            String contentType = imagePart.getContentType();

            String extension;

            if ("image/jpeg".equalsIgnoreCase(contentType)) {
                extension = ".jpg";
            } else if ("image/png".equalsIgnoreCase(contentType)) {
                extension = ".png";
            } else if ("image/webp".equalsIgnoreCase(contentType)) {
                extension = ".webp";
            } else {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Choose a JPG, PNG, or WEBP image."
                );
                return;
            }

            // Store uploads in the web application's uploads directory.
            String uploadDirectory =
                    getServletContext().getRealPath("/images");
            System.out.println("Upload directory: " + uploadDirectory);
            System.out.println("Context path: " + getServletContext().getContextPath());

            if (uploadDirectory == null) {
                response.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Upload directory is unavailable."
                );
                return;
            }

            Path uploadPath = Paths.get(uploadDirectory);
            Files.createDirectories(uploadPath);

            // Generate a safe unique filename; never trust the original name.
            savedImageName = UUID.randomUUID().toString()
                    + extension;

            Path destination = uploadPath.resolve(savedImageName)
                    .normalize();

            if (!destination.startsWith(uploadPath.normalize())) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid upload filename."
                );
                return;
            }

            try (InputStream input = imagePart.getInputStream()) {
                Files.copy(
                        input,
                        destination,
                        StandardCopyOption.REPLACE_EXISTING
                );
            }

            Equipment equipment = new Equipment(
                    name.trim(),
                    category.trim(),
                    brand,
                    model,
                    description,
                    pricePerDay,
                    location.trim(),
                    savedImageName,
                    availability
            );

            equipment.setOwnerId(ownerId);

            equipmentDAO.saveEquipment(equipment);

            response.sendRedirect(
                    request.getContextPath() + "/equipmentList"
            );

        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid price per day."
            );

        } catch (Exception e) {
            // Avoid leaving an orphaned file if saving equipment fails.
            if (savedImageName != null) {
                String uploadDirectory =
                        getServletContext().getRealPath("/uploads");

                if (uploadDirectory != null) {
                    try {
                        Files.deleteIfExists(
                                Paths.get(uploadDirectory, savedImageName)
                        );
                    } catch (IOException ignored) {
                        // The original error is more useful for debugging.
                    }
                }
            }

            throw new ServletException(
                    "Could not add equipment.", e
            );
        }
    }
}

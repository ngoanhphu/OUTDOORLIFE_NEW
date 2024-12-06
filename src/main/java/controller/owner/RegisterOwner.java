package controller.owner;

import dao.OwnerDAO;
import dao.DBContext;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import model.Owner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@MultipartConfig
@WebServlet("/owner/registerOwner")
public class RegisterOwner extends HttpServlet {
    private static final String UPLOAD_DIR = "src/main/webapp/docs/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());

        try {
            String status = ownerDAO.getOwnerStatusByAccountId(session);
            if ("approved".equals(status)) {
                request.setAttribute("message", "Already became an approved owner!");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            else if ("pending".equals(status)) {
                request.setAttribute("message", "Already submitted an owner registration request!");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            else {
                request.getRequestDispatcher("registerOwner.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Owner owner = new Owner();
            owner.setAccountId((Integer) session.getAttribute("accountId"));
            owner.setOccupation(request.getParameter("occupation"));
            owner.setAddress(request.getParameter("address"));

            // Parse and set date of birth
            String dobStr = request.getParameter("dob");
            if (dobStr != null && !dobStr.isEmpty()) {
                try {
                    owner.setDob(Date.valueOf(dobStr));
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid date of birth format");
                }
            } else {
                throw new IllegalArgumentException("Invalid date of birth");
            }

            owner.setGender(request.getParameter("gender"));
            owner.setRegion(request.getParameter("region"));
            owner.setIdentification(Integer.parseInt(request.getParameter("identification")));
            owner.setTaxCode(Integer.parseInt(request.getParameter("taxCode")));

            Part filePart = request.getPart("fileInput");
            String originalFileName = extractFileName(filePart);
            String uniqueFileName = generateUniqueFileName(originalFileName);

            Path uploadPath = Paths.get(getServletContext().getRealPath(""));
            System.out.println("Upload Path: " + uploadPath);
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(uniqueFileName);
            System.out.println("File Path: " + filePath);

            try (InputStream inputStream = filePart.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            owner.setImage(uniqueFileName);

            // Parse and set start date
            String startDateStr = request.getParameter("startDate");
            if (startDateStr != null && !startDateStr.isEmpty()) {
                owner.setStartDate(new Date(dateFormat.parse(startDateStr).getTime()));
            } else {
                throw new IllegalArgumentException("Invalid start date");
            }

            // Parse and set end date
            String endDateStr = request.getParameter("endDate");
            if (endDateStr != null && !endDateStr.isEmpty()) {
                owner.setEndDate(new Date(dateFormat.parse(endDateStr).getTime()));
            } else {
                throw new IllegalArgumentException("Invalid end date");
            }

            owner.setNotes(request.getParameter("notes"));

            boolean isAdded = ownerDAO.addOwner(owner, session);
            if (isAdded) {
                response.sendRedirect("success.jsp");
            } else {
                request.setAttribute("errorMessage", "Failed to register owner.");
                request.getRequestDispatcher("owner/registerOwner.jsp").forward(request, response);
            }
        } catch (ParseException e) {
            throw new ServletException("Date parsing error", e);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String token : contentDisp.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    // Generate a unique filename to prevent overwriting
    private String generateUniqueFileName(String originalFileName) {
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = originalFileName.substring(dotIndex);
        }
        return UUID.randomUUID().toString() + extension;
    }
}
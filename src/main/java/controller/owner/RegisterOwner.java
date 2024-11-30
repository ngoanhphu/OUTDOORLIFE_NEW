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
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@MultipartConfig
@WebServlet("/owner/registerOwner")
public class RegisterOwner extends HttpServlet {
    private static final String UPLOAD_DIR = "src/main/webapp/img/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("owner/registerOwner.jsp").forward(request, response);
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

            // Handle file upload
            Part filePart = request.getPart("fileInput");
            String projectPath = System.getProperty("user.dir");
            String imgUploadPath = projectPath + File.separator + UPLOAD_DIR;
            createDirectoryIfNotExists(imgUploadPath);
            String imageDocPath = saveFile(filePart, imgUploadPath);
            String relativeDocPath = UPLOAD_DIR + File.separator + imageDocPath;
            owner.setImage(relativeDocPath);

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

    private void createDirectoryIfNotExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private String saveFile(Part filePart, String uploadPath) throws IOException {
        String fileName = extractFileName(filePart);
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);
        return fileName;
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
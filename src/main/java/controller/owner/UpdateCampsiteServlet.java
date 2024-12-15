package controller.owner;

import dao.CampsiteDAO;
import java.io.File;
import java.io.IOException;

import dao.DBContext;
import dao.OwnerDAO;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Campsite;
import model.User;

@WebServlet(name = "UpdateCampsiteServlet", urlPatterns = {"/update-campsite"})
@MultipartConfig
public class UpdateCampsiteServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "D:\\OJT\\new_project\\OUTDOORLIFE_NEW\\src\\main\\webapp\\img";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
        User auth = (User) request.getSession().getAttribute("currentUser");

        if (auth == null) {
            session.setAttribute("message", "You are not logged in!");
            response.sendRedirect("login.jsp");
        } else {
            UserDaoImpl userDAO = new UserDaoImpl();
            boolean isDeactivated = userDAO.isAccountDeactivated(auth.getId());
            if (isDeactivated) {
                session.setAttribute("message", "Your account has been deactivated!");
                response.sendRedirect("loginMessage");
            }

            int campsiteId = Integer.parseInt(request.getParameter("id"));
            CampsiteDAO campsiteDAO = new CampsiteDAO();
            Campsite campsite = campsiteDAO.getCampsiteById(campsiteId);  // Get campsite info by ID

            if (campsite != null) {
                request.setAttribute("c", campsite);  // Set campsite data in request
                request.getRequestDispatcher("/updateCampsite.jsp").forward(request, response);  // Forward to update form
            } else {
                response.sendRedirect("manage-campsite");  // If no campsite found, redirect to management page
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the form data
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String image = request.getParameter("image");  // Image is optional in case user doesn't upload new image
        String limit = request.getParameter("limit");
        String status = request.getParameter("status");

        // Handle file upload
        Part filePart = request.getPart("image"); // Get the file part from the form
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = extractFileName(filePart);
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();  // Create the directory if it doesn't exist
            }

            // Save the file to the specified directory
            String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
            filePart.write(filePath);
            image = fileName;  // Update image path to the new file name
        }

        try {
            // Create a new Campsite object with updated values
            CampsiteDAO cdao = new CampsiteDAO();
            Campsite campsite = new Campsite();
            campsite.setCampId(Integer.parseInt(id));
            campsite.setCampName(name);
            campsite.setCampPrice(Integer.parseInt(price));
            campsite.setCampAddress(address);
            campsite.setCampDescription(description);
            campsite.setCampImage(image);  // Set the image path (it may be new or unchanged)
            campsite.setCampStatus(status.equals("1"));  // Convert string status to boolean
            campsite.setLimite(Integer.parseInt(limit));

            // Update the campsite in the database
            cdao.updateCampsite(campsite);

        } catch (Exception e) {
            e.printStackTrace();  // Log any exceptions for debugging
        }

        // Redirect to the manage-campsite page
        response.sendRedirect("manage-campsite");
    }

    // Helper method to extract file name from the uploaded Part
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Servlet for updating campsite details including image upload.";
    }
}

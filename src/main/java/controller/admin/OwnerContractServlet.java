//package controller.admin;
//
//import dao.DBContext;
//import dao.OwnerDAO;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import model.OwnerContract;
//
//import java.io.IOException;
//import java.sql.Date;
//import java.sql.SQLException;
//
//@WebServlet("/owner/OwnerContractServlet")
//public class OwnerContractServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private OwnerDAO dao;
//
//    public void init() throws ServletException {
//        try {
//            DBContext db = new DBContext();
//            dao = new OwnerDAO(db);
//        } catch (Exception e) {
//            throw new ServletException("Failed to initialize OwnerDAO", e);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            // Create OwnerContract object from form data
//            OwnerContract contract = new OwnerContract();
//
//            // Retrieve accountId and ownerId from session
//            HttpSession session = request.getSession();
//            Integer accountId = (Integer) session.getAttribute("accountId");
//            Integer ownerId = (Integer) session.getAttribute("ownerId");
//
//            if (accountId == null || ownerId == null) {
//                throw new IllegalArgumentException("Account_id or owner_id is not present in the session");
//            }
//
//            contract.setAccountId(accountId);
//            contract.setOwnerId(ownerId);
//
//            String startDateStr = request.getParameter("startDate");
//            if (startDateStr != null && !startDateStr.isEmpty()) {
//                contract.setStartDate(Date.valueOf(startDateStr));
//            }
//
//            String endDateStr = request.getParameter("endDate");
//            if (endDateStr != null && !endDateStr.isEmpty()) {
//                contract.setEndDate(Date.valueOf(endDateStr));
//            }
//
//            contract.setNotes(request.getParameter("notes"));
//
//            // Insert contract into database
//            boolean success = insertOwnerContract(contract, session);
//
//            // Redirect based on success
//            if (success) {
//                response.sendRedirect(request.getContextPath() + "/success.jsp");
//            } else {
//                response.sendRedirect(request.getContextPath() + "/error.jsp");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect(request.getContextPath() + "/error.jsp");
//        }
//    }
//
//    private boolean insertOwnerContract(OwnerContract contract, HttpSession session) throws SQLException {
//        return dao.insertOwnerContract(contract, session);
//    }
//}
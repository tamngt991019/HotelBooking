/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import project.daos.UserDAO;
import project.dtos.UserDTO;
import project.dtos.UserErrorDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class RegisterController extends HttpServlet {
    private final Logger log4j = Logger.getLogger(MainController.class);
    private final String SUCCESS = "login.jsp";
    private final String ERROR = "register.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {
            String userID = request.getParameter("txtUserID");
            String userName = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRePassword");
            UserErrorDTO errorObject = new UserErrorDTO();
            boolean check = true;
            UserDAO dao = new UserDAO();
            if (userID.isEmpty()) {
                errorObject.setUserIDError("User ID cannot empty !");
                check = false;
            }
            boolean checkID = dao.checkExist(userID);
            if (checkID) {
                errorObject.setUserIDError("Dupplicate ID !");
                check = false;
            }
            if (userName.isEmpty()) {
                errorObject.setUserNameError("User Name cannot empty !");
                check = false;
            }
            if (password.isEmpty()) {
                errorObject.setPasswordError("password cannot empty !");
                check = false;
            }
            if (rePassword.isEmpty()) {
                errorObject.setRePasswordError("re-password cannot empty !");
                check = false;
            }
            if (!rePassword.equals(password)) {
                errorObject.setRePasswordError("re-password does not match with password !");
                check = false;
            }
           
            if (check) {
                dao.create(new UserDTO(userID, userName, password, "UR"));
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR_OBJECT", errorObject);
            }
        } catch (Exception e) {
            log4j.error("Error at RegisterController : ",e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

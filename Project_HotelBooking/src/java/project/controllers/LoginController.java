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
import javax.servlet.http.HttpSession;
import project.daos.UserDAO;
import project.dtos.UserDTO;
import project.dtos.UserErrorDTO;
import org.apache.log4j.Logger;
/**
 *
 * @author Kami.Sureiya
 */
public class LoginController extends HttpServlet {
private final Logger log4j = Logger.getLogger(LoginController.class);
    private final String ADMIN = "admin.jsp";
    private final String USER = "ManageRoomController";
    private final String ERROR = "login.jsp";
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
        boolean check = true;
        boolean s = true;
        try {
            String userID = request.getParameter("txtUserID");
            String pass = request.getParameter("txtPassword");
            UserErrorDTO errorObject = new UserErrorDTO();

            if (userID.isEmpty()) {
                errorObject.setUserIDError(" User ID cannot blank !");
                check = false;
            }
            if (pass.isEmpty()) {
                errorObject.setPasswordError(" Password cannot blank !");
                check = false;
            }
            if (check) {
                UserDTO user = new UserDTO(userID, pass);
                UserDAO dao = new UserDAO();
                UserDTO loginUser = dao.checkLogin(user);
                if (loginUser != null) {
                    if (loginUser.getRoleID().equals("AD")) {
                        url = ADMIN;
                    } else if (loginUser.getRoleID().equals("UR")) {

                        url = USER;
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("ACCOUNT", loginUser);
                } else {
                    request.setAttribute("LOGIN_ERROR", "Your userID or password is incorrect !");
                    check = false;
                }
            } else {
                request.setAttribute("ERROR_OBJECT", errorObject);
            }
        } catch (Exception e) {
            log4j.error("Error at LoginController : ",e);
        } finally {
            if (check) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import project.daos.UserDAO;
import project.dtos.UserDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class SearchUserController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(SearchUserController.class);
    private final String SUCCESS = "userManagement.jsp";
    private final String ERROR = "ManageUserController";

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
            HttpSession session = request.getSession();
            String search = request.getParameter("txtSearch");
            if (search.isEmpty()) {
                url = ERROR;
                request.setAttribute("SEARCH_USER_ERROR", "Please input somethings to search !");
            } else {
                UserDAO dao = new UserDAO();
                List<UserDTO> list = dao.searchByUserName(search, 1, 3);
                if (list.isEmpty() || list == null) {
                    list = dao.searchByUserName("", 1, 3);
                    request.setAttribute("SEARCH_USER_ERROR", "Not Found");
                }

                session.setAttribute("FROM", 1);
                session.setAttribute("TO", 3);
                session.setAttribute("PAGENUM", 1);

                request.setAttribute("SEARCHUSER", list);
                url = SUCCESS;

            }
            session.setAttribute("SEARCH_USER_VALUE", search);
        } catch (Exception e) {
            log4j.error("Error at SearchUserController : ", e);
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

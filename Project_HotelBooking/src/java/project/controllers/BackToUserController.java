/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import project.daos.RoomDAO;
import project.dtos.RoomDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class BackToUserController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(BackToUserController.class);
    private final String SUCCESS = "user.jsp";
    private final String ERROR = "ManageRoomController";

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
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            int from = 1;
            int to = 3;

            if (session.getAttribute("FROM") != null && session.getAttribute("TO") != null && session.getAttribute("PAGENUM") != null) {
                from = (int) session.getAttribute("FROM");
                to = (int) session.getAttribute("TO");
                RoomDAO roomDAO = new RoomDAO();
                List<RoomDTO> list = new ArrayList<>();
                if (session.getAttribute("SEARCHBYDATE") != null) {
                    String address = (String) session.getAttribute("ADDRESS");
                    int amount = (int) session.getAttribute("AMOUNT");
                    list = roomDAO.searchByDate(address, amount, from, to);
                } else {
                    String searchValue = (String) session.getAttribute("SEARCH_ROOM_VALUE");
                    if(searchValue == null || searchValue.isEmpty()){
                        searchValue = "";
                    }
                    list = roomDAO.searchByHotelName(searchValue, from, to);
                }
                url = SUCCESS;
                request.setAttribute("SEARCHROOM", list);
            } else {
                url = ERROR;
            }
        } catch (Exception e) {
            log4j.error("Error at BackToUserController : ", e);
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

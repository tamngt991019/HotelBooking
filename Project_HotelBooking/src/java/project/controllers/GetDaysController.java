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
import org.apache.log4j.Logger;
import project.dtos.DateDTO;
import project.dtos.DateErrorDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class GetDaysController extends HttpServlet {
private final Logger log4j = Logger.getLogger(GetDaysController.class);
    private final String SUCCESS = "viewCart.jsp";

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
        try {
            HttpSession session = request.getSession();
            String checkIn = request.getParameter("txtCheckIn");
            String checkOut = request.getParameter("txtCheckOut");
            long dayNum = 1;
            DateErrorDTO dateError = new DateErrorDTO();
            DateDTO dateDTO = new DateDTO();
            boolean validDate = dateDTO.isValidDate(dateError, checkIn, checkOut);
            if (validDate) {
                checkIn = dateDTO.getCheckInDate();
                checkOut = dateDTO.getCheckOutDate();
                dayNum = dateDTO.getNoOfDays();
            } else {
                request.setAttribute("DATE_ERROR", dateError);
            }
            session.setAttribute("CHECKIN", checkIn);
            session.setAttribute("CHECKOUT", checkOut);
            session.setAttribute("DAYNUM", dayNum);
        } catch (Exception e) {
            log4j.error("Error at GetDaysController : ", e);
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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

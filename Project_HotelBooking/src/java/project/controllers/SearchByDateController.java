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
import project.dtos.DateDTO;
import project.dtos.DateErrorDTO;
import project.dtos.RoomDTO;
import project.dtos.UserDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class SearchByDateController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(SearchByDateController.class);
    private final String USER = "user.jsp";
    private final String ADMIN = "roomManagement.jsp";
    private final String ERROR = "ManageRoomController";
    private final String HOME = "home.jsp";

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
        String url = HOME;
        try {
            boolean check = true;
            boolean isSearchBtDate = true;
            HttpSession session = request.getSession();
            String address = request.getParameter("txtAddress");
            if (address.isEmpty()) {
                request.setAttribute("ADDRESS_ERROR", "Please input area to search");
                check = false;
                isSearchBtDate = false;
            }
            int amount = 0;
            try {
                amount = Integer.parseInt(request.getParameter("txtAmount"));
                if (amount < 1) {
                    request.setAttribute("AMOUNT_ERROR", "Amount must be greater than 0 !");
                    check = false;
                    isSearchBtDate = false;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("AMOUNT_ERROR", "Amount must be a positive number !");
                check = false;
                isSearchBtDate = false;
            }
            String checkIn = request.getParameter("txtCheckIn");
            String checkOut = request.getParameter("txtCheckOut");

            DateErrorDTO dateError = new DateErrorDTO();
            DateDTO dateDTO = new DateDTO();
            boolean validDate = dateDTO.isValidDate(dateError, checkIn, checkOut);

            List<RoomDTO> list = new ArrayList<>();
            RoomDAO dao = new RoomDAO();
            if (check == true && validDate == true && isSearchBtDate == true) {
                list = dao.searchByDate(address, amount, 1, 3);

                session.setAttribute("FROM", 1);
                session.setAttribute("TO", 3);
                session.setAttribute("PAGENUM", 1);
                request.setAttribute("SEARCHROOM", list);

                session.setAttribute("CHECKIN", dateDTO.getCheckInDate());
                session.setAttribute("CHECKOUT", dateDTO.getCheckOutDate());
                session.setAttribute("DAYNUM", dateDTO.getNoOfDays());

                UserDTO user = (UserDTO) session.getAttribute("ACCOUNT");
                if (user != null) {
                    String roleID = user.getRoleID();
                    if (roleID.equals("AD")) {
                        url = ADMIN;
                    } else if (roleID.equals("UR")) {
                        url = USER;
                    } else if (roleID.equals("GUESS")) {
                        url = HOME;
                    }
                }
                if (list.isEmpty() || list == null) {
                    request.setAttribute("SEARCH_DATE_ERROR", "Not Found");
                    isSearchBtDate = false;
                    url = ERROR;
                }
            } else {
                request.setAttribute("DATE_ERROR", dateError);
                isSearchBtDate = false;
                url = ERROR;
            }

            session.setAttribute("SEARCHBYDATE", isSearchBtDate);
            session.setAttribute("ADDRESS", address);
            session.setAttribute("AMOUNT", amount);

            session.removeAttribute("SEARCH_ROOM_VALUE");
        } catch (Exception e) {
            log4j.error("Error at SearchByDateController : ", e);
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

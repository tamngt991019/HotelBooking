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
import project.dtos.UserDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class PreviousRoomController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(PreviousRoomController.class);
    private final String ADMIN = "roomManagement.jsp";
    private final String USER = "user.jsp";
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
        String url = "";
        try {
            HttpSession session = request.getSession();
            String search = request.getParameter("txtSearch");
            String address = request.getParameter("txtAddress");
            String amountStr = request.getParameter("txtAmount");
            int amount = 1;
            boolean isSearchByDate = false;
            if (session.getAttribute("SEARCHBYDATE") != null) {
                isSearchByDate = (boolean) session.getAttribute("SEARCHBYDATE");
                if (address.isEmpty() || address == null || amountStr.isEmpty() || amountStr == null) {
                    isSearchByDate = false;
                } else {
                    amount = Integer.parseInt(amountStr);
                    if (amount < 1) {
                        isSearchByDate = false;
                    }
                }
                if (isSearchByDate) {
                    session.setAttribute("ADDRESS", address);
                    session.setAttribute("AMOUNT", amount);
                    session.setAttribute("SEARCHBYDATE", isSearchByDate);
                } else {
                    session.removeAttribute("ADDRESS");
                    session.removeAttribute("AMOUNT");
                    session.removeAttribute("SEARCHBYDATE");
                }
            }
            RoomDAO dao = new RoomDAO();
            List<RoomDTO> list = new ArrayList<>();

            int pageNum = (int) session.getAttribute("PAGENUM");
            int from = (int) session.getAttribute("FROM");
            int to = (int) session.getAttribute("TO");

            if (pageNum == 1) {
                if (isSearchByDate) {
                    list = dao.searchByDate(address, amount, from, to);
                } else {
                    list = dao.searchByHotelName(search, from, to);
                }
            } else {
                pageNum--;
                to = from - 1;
                from -= 3;
                if (from < 1) {
                    from = 1;
                    if (isSearchByDate) {
                        list = dao.searchByDate(address, amount, from, to);
                    } else {
                        list = dao.searchByHotelName(search, from, to);
                    }
                } else {
                    if (isSearchByDate) {
                        list = dao.searchByDate(address, amount, from, to);
                    } else {
                        list = dao.searchByHotelName(search, from, to);
                    }
                }
                session.setAttribute("FROM", from);
                session.setAttribute("TO", to);
                session.setAttribute("PAGENUM", pageNum);
            }

            request.setAttribute("SEARCHROOM", list);

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
        } catch (Exception e) {
            log4j.error("Error at PreviousRoomController : ", e);
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

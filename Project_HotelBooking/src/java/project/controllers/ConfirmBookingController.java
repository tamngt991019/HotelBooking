/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import project.daos.BookingDAO;
import project.daos.BookingDetailDAO;
import project.daos.RoomDAO;
import project.dtos.BookingDTO;
import project.dtos.BookingDetailDTO;
import project.dtos.CartDTO;
import project.dtos.DateDTO;
import project.dtos.DateErrorDTO;
import project.dtos.RoomDTO;
import project.dtos.UserDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class ConfirmBookingController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(ConfirmBookingController.class);
    private final String ERROR = "viewCart.jsp";
    private final String SUCCESS = "ManageRoomController";

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
            boolean check = true;
            HttpSession session = request.getSession();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            DateErrorDTO dateError = new DateErrorDTO();
            DateDTO dateDTO = new DateDTO();

            String checkIn = request.getParameter("txtCheckIn");
            String checkOut = request.getParameter("txtCheckOut");

            boolean validDate = dateDTO.isValidDate(dateError, checkIn, checkOut);
            if (!validDate) {
                request.setAttribute("DATE_ERROR", dateError);
                check = false;
            } else {
                checkIn = dateDTO.getCheckInDate();
                checkOut = dateDTO.getCheckOutDate();
            }
            BookingDAO bookingDAO = new BookingDAO();
            boolean checkQuantity = true;
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart != null) {
                int count = 1;
                for (HashMap.Entry<Long, RoomDTO> hm : cart.getCart().entrySet()) {
                    RoomDAO roomDAO = new RoomDAO();
                    if (!roomDAO.isRoomExisted(hm.getKey())) {
                        request.setAttribute("ROOMERROR", "Room No." + count + " is unavailable now !");
                        check = false;
                    } else {
                        int bookedRoom = bookingDAO.getBookedRoom(hm.getKey(), sdf.format(new Date()), checkIn, checkOut);
                        checkQuantity = bookingDAO.checkQuantity(hm.getKey(), bookedRoom, hm.getValue().getQuantity());
                        if (!checkQuantity) {
                            request.setAttribute("ROOMERROR", "Room No." + count + " dont have enough room to book !");
                            check = false;
                        }
                    }
                }

                if (check) {
                    double totalAll = Double.parseDouble(request.getParameter("txtTotalAll"));
                    UserDTO user = (UserDTO) session.getAttribute("ACCOUNT");
                    if (user != null) {
                        BookingDTO bookingDTO = new BookingDTO(user.getUserID(), totalAll, sdf.format(new Date()));
                        bookingDAO.insertBooking(bookingDTO);
                        long bookingID = bookingDAO.getLastBookingID();
                        BookingDetailDAO bdDAO = new BookingDetailDAO();
                        for (Map.Entry<Long, RoomDTO> hm : cart.getCart().entrySet()) {
                            BookingDetailDTO bdDTO = new BookingDetailDTO(bookingID, hm.getKey(), hm.getValue().getQuantity(), hm.getValue().getPrice(), checkIn, checkOut);
                            bdDAO.insertBookingDeatail(bdDTO);
                        }
                    }
                    session.removeAttribute("CART");
                    session.removeAttribute("SEARCH_ROOM_VALUE");
                    session.removeAttribute("SEARCHBYDATE");
                    session.removeAttribute("ADDRESS");
                    session.removeAttribute("AMOUNT");
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log4j.error("Error at ConfirmBookingController : ", e);
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

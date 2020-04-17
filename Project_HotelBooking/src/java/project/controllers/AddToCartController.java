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
import project.dtos.CartDTO;
import project.dtos.RoomDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class AddToCartController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(AddToCartController.class);
    private final String SUCCESS = "user.jsp";

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
                        amount = 1;
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
            
            long roomID = Long.parseLong(request.getParameter("txtRoomID"));
            long hotelID = Long.parseLong(request.getParameter("txtHotelID"));
            String typeID = request.getParameter("txtTypeID");
            double price = Double.parseDouble(request.getParameter("txtPrice"));
            RoomDTO room = new RoomDTO(roomID, hotelID, typeID, amount, price);
            
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartDTO();
            }
            cart.add(room);
            session.setAttribute("CART", cart);
            request.setAttribute("ADDOK", "Add to cart successfully !");
            
            int from = 1;
            int to = 3;
            int pageNum = 1;
            if (session.getAttribute("FROM") != null && session.getAttribute("TO") != null && session.getAttribute("PAGENUM") != null) {
                from = (int) session.getAttribute("FROM");
                to = (int) session.getAttribute("TO");
                pageNum = (int) session.getAttribute("PAGENUM");
            }
            RoomDAO dao = new RoomDAO();
            List<RoomDTO> list = new ArrayList<>();
            int maxRow = 0;
            if (isSearchByDate) {
                maxRow = dao.getNoOfActiveRoomsByDate(address, amount);
            } else {
                maxRow = dao.getNoOfActiveRoomsByHotelName(search);
            }
            int noOfPages = maxRow / 3;
            if (maxRow % 3 != 0) {
                noOfPages += 1;
            }
            if (pageNum == noOfPages) {
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
            request.setAttribute("SEARCHROOM", list);
            
        } catch (Exception e) {
            log4j.error("Error at AddToCartController : ", e);
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

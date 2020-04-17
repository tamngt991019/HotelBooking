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
import project.daos.RoomDAO;
import project.dtos.RoomDTO;
import project.dtos.RoomErrorDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class CreateRoomController extends HttpServlet {
private final Logger log4j = Logger.getLogger(CreateRoomController.class);
    private final String SUCCESS = "createRoom.jsp";

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
            long hotelID = Long.parseLong(request.getParameter("cbxHotelID"));;
            String typeID = request.getParameter("cbxTypeID");
            int quantity = 0;
            double price = 0;
            RoomDAO dao = new RoomDAO();
            RoomErrorDTO roomError = new RoomErrorDTO();
            boolean check = true;
            if (hotelID == 0) {
                roomError.setHotelIDError("Pleaser select hotelID !");
                check = false;
            }
            if (typeID.equals("0")) {
                roomError.setTypeRoomError("Pleaser select typeID !");
                check = false;
            }
            boolean checkType = dao.checkRoomTypeOfHotel(new RoomDTO(0, hotelID, typeID, 0, 0));
            if(checkType){
                roomError.setTypeRoomError("Hotel "+hotelID+" already exists in room type: "+typeID);
                check = false;
            }
            try {
                quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                if (quantity < 1) {
                    roomError.setQuantityError("Quantity must be greater than 0 !");
                    check = false;
                }
            } catch (NumberFormatException e) {
                roomError.setQuantityError("Quantity must be a positive number !");
                check = false;
            }
            try {
                price = Double.parseDouble(request.getParameter("txtUnitPrice"));
                if (price < 1) {
                    roomError.setPriceError("Price must be greater than 0 !");
                    check = false;
                }
            } catch (NumberFormatException e) {
                roomError.setPriceError("Price must be a positive number !");
                check = false;
            }
            if(check){
                RoomDTO room = new RoomDTO(hotelID, hotelID, typeID, quantity, price, true);
                dao.insertRoom(room);
                request.setAttribute("CREATEOK", "Create room successfully !");
            } else {
                request.setAttribute("ERROR_OBJECT", roomError);
            }
        } catch (Exception e) {
            log4j.error("Error at CreateRoomController : ",e);
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

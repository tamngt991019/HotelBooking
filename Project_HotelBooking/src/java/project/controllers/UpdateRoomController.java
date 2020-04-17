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
public class UpdateRoomController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(UpdateRoomController.class);
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
        String url = SUCCESS;
        try {
            long roomID = Long.parseLong(request.getParameter("txtRoomID"));
            String typeID = request.getParameter("txtTypeID");
            String quantityStr = request.getParameter("txtQuantity");
            String priceStr = request.getParameter("txtPrice");
            RoomErrorDTO errorObject = new RoomErrorDTO();
            boolean check = true;
            errorObject.setRoomIDError("Update error at RoomID : " + String.valueOf(roomID));
            if (typeID.isEmpty()) {
                errorObject.setTypeRoomError("Type Room cannot blank !");
                check = false;
            }
            if (quantityStr.isEmpty()) {
                errorObject.setQuantityError("Quantity cannot blank !");
                check = false;
            }
            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityStr.trim());
                if (quantity < 0) {
                    errorObject.setQuantityError("Quantity must be geater than 0 !");
                    check = false;
                }
            } catch (NumberFormatException e) {
                errorObject.setQuantityError("Quantity must be a positive number !");
                check = false;
            }
            if (priceStr.isEmpty()) {
                errorObject.setPriceError("Price cannot blank !");
                check = false;
            }
            double price = 0;
            try {
                price = Double.parseDouble(priceStr.trim());
                if (price < 0) {
                    errorObject.setPriceError("Price must be geater than 0 !");
                    check = false;
                }
            } catch (NumberFormatException e) {
                errorObject.setPriceError("Price must be a positive number !");
                check = false;
            }
            if (check) {
                RoomDAO dao = new RoomDAO();
                RoomDTO room = new RoomDTO(roomID, 0, typeID, quantity, price);
                dao.updateRoom(room);
            } else {
                request.setAttribute("UPDATE_ERROR", errorObject);
            }
        } catch (Exception e) {
            log4j.error("Error at UpdateRoomController : ", e);
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

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

/**
 *
 * @author Kami.Sureiya
 */
public class MainController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(MainController.class);
    private final String LOGIN = "LoginController";
    private final String LOGOUT = "LogoutController";
    private final String ERROR = "login.jsp";

    private final String BACKADMIN = "BackToAdminController";
    private final String MANAGEROOM = "ManageRoomController";
    private final String SEARCHROOM = "SearchRoomController";
    private final String DELETEROOM = "DeleteRoomController";
    private final String UPDATEROOM = "UpdateRoomController";
    private final String CREATEROOM = "CreateRoomController";
    private final String NEXTROOM = "NextRoomController";
    private final String PREVROOM = "PreviousRoomController";
    private final String SEARCHBYDATE = "SearchByDateController";

    private final String MANAGEUSER = "ManageUserController";
    private final String SEARCHUSER = "SearchUserController";
    private final String NEXTUSER = "NextUserController";
    private final String PREVUSER = "PreviousUserController";
    private final String DELETEUSER = "DeleteUserController";
    private final String UPDATEUSER = "UpdateUserController";

    private final String ADDCART = "AddToCartController";
    private final String VIEWCART = "ViewCartController";
    private final String REMOVECART = "RemoveCartController";
    private final String UPDATECART = "UpdateCartController";
    private final String GETDAYS = "GetDaysController";
    private final String BACKUSER = "BackToUserController";
    private final String CLEARCART = "ClearCartController";
    private final String CONFIRMBOOKING = "ConfirmBookingController";
    private final String REGISTER = "RegisterController";

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
            String action = request.getParameter("btnAction");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            }//==================================================MANAGE ROOM
            else if (action.equals("BackToAdmin")) {
                url = BACKADMIN;
            } else if (action.equals("ManageRoom")) {
                url = MANAGEROOM;
            } else if (action.equals("SearchRoom")) {
                url = SEARCHROOM;
            } else if (action.equals("DeleteRoom")) {
                url = DELETEROOM;
            } else if (action.equals("UpdateRoom")) {
                url = UPDATEROOM;
            } else if (action.equals("CreateRoom")) {
                url = CREATEROOM;
            } else if (action.equals("NextRoom")) {
                url = NEXTROOM;
            } else if (action.equals("PreviousRoom")) {
                url = PREVROOM;
            } //==================================================MANAGE CART
            else if (action.equals("AddToCart")) {
                url = ADDCART;
            } else if (action.equals("ViewCart")) {
                url = VIEWCART;
            } else if (action.equals("RemoveCart")) {
                url = REMOVECART;
            } else if (action.equals("UpdateCart")) {
                url = UPDATECART;
            } else if (action.equals("SearchByDate")) {
                url = SEARCHBYDATE;
            } else if (action.equals("GetDays")) {
                url = GETDAYS;
            } else if (action.equals("BackToUser")) {
                url = BACKUSER;
            } else if (action.equals("ClearCart")) {
                url = CLEARCART;
            } else if (action.equals("ConfirmBooking")) {
                url = CONFIRMBOOKING;
            } else if (action.equals("Register")) {
                url = REGISTER;
            }//==================================================MANAGE USER 
            else if (action.equals("ManageUser")) {
                url = MANAGEUSER;
            } else if (action.equals("SearchUser")) {
                url = SEARCHUSER;
            } else if (action.equals("NextUser")) {
                url = NEXTUSER;
            } else if (action.equals("PreviousUser")) {
                url = PREVUSER;
            } else if (action.equals("DeleteUser")) {
                url = DELETEUSER;
            } else if (action.equals("UpdateUser")) {
                url = UPDATEUSER;
            }

        } catch (Exception e) {
            log4j.error("Error at MainController : ", e);
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

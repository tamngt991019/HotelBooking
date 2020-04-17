/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import project.daos.HotelDAO;
import project.daos.RoomDAO;
import project.daos.TypeRoomDAO;
import project.dtos.DateDTO;
import project.dtos.HotelDTO;
import project.dtos.RoomDTO;
import project.dtos.TypeRoomDTO;
import project.dtos.UserDTO;

/**
 *
 * @author Kami.Sureiya
 */
public class ManageRoomController extends HttpServlet {

    private final Logger log4j = Logger.getLogger(ManageRoomController.class);
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
        String url = HOME;
        try {
            HttpSession session = request.getSession();

            RoomDAO roomDAO = new RoomDAO();
            List<RoomDTO> listRoom = roomDAO.searchByHotelName("", 1, 3);
            request.setAttribute("SEARCHROOM", listRoom);

            HotelDAO hotelDAO = new HotelDAO();
            List<HotelDTO> listHotel = hotelDAO.loadHotel();
            session.setAttribute("HOTEL", listHotel);

            TypeRoomDAO typeDAO = new TypeRoomDAO();
            List<TypeRoomDTO> listTypel = typeDAO.loadTypeRoom();
            session.setAttribute("TYPE", listTypel);

            session.setAttribute("FROM", 1);
            session.setAttribute("TO", 3);
            session.setAttribute("PAGENUM", 1);

            if (session.getAttribute("CART") == null) {
                DateDTO dateDTO = new DateDTO();
                dateDTO.getDefaultDate(new Date());
                session.setAttribute("CHECKIN", dateDTO.getCheckInDate());
                session.setAttribute("CHECKOUT", dateDTO.getCheckOutDate());
                session.setAttribute("DAYNUM", dateDTO.getNoOfDays());
            }

            UserDTO user = (UserDTO) session.getAttribute("ACCOUNT");
            if (user != null) {
                String roleID = user.getRoleID();
                if (roleID.equals("AD")) {
                    url = ADMIN;
                } else if (roleID.equals("UR")) {
                    url = USER;
                } else if(roleID.equals("GUESS")){
                    url = HOME;
                }
            }
            
        } catch (Exception e) {
            log4j.error("Error at ManageRoomController : ", e);
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

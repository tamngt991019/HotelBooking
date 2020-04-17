/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import project.dtos.BookingDetailDTO;
import project.utils.DBConnection;

/**
 *
 * @author Kami.Sureiya
 */
public class BookingDetailDAO {
    private final Logger log4j = Logger.getLogger(BookingDetailDAO.class);
    private DBConnection db = new DBConnection();
    private Connection cn = null;
    private PreparedStatement pStm = null;
    private ResultSet rs = null;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pStm != null) {
                pStm.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            log4j.error("Error at closeConnection : ",e);
//            e.printStackTrace();
        }
    }
    
    public void insertBookingDeatail(BookingDetailDTO dto) {
        String sql = "Insert INTO tblBookingDetails(bookingID, roomID, amount, unitPrice, checkInDate, checkOutDate) Values(?,?,?,?,?,?)";
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            pStm.setLong(1, dto.getBookingID());
            pStm.setLong(2, dto.getRoomID());
            pStm.setInt(3, dto.getAmount());
            pStm.setDouble(4, dto.getPrice());
            pStm.setString(5, dto.getCheckInDate());
            pStm.setString(6, dto.getCheckOutDate());
            pStm.executeUpdate();
        } catch (Exception e) {
            log4j.error("Error at insertBookingDeatail : ", e);
        } finally {
            closeConnection();
        }
    }
}

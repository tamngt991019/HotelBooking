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
import project.dtos.BookingDTO;
import project.utils.DBConnection;

/**
 *
 * @author Kami.Sureiya
 */
public class BookingDAO {

    private final Logger log4j = Logger.getLogger(BookingDAO.class);
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
            log4j.error("Error at closeConnection : ", e);
        }
    }

    public void insertBooking(BookingDTO dto) {
        String sql = "Insert INTO tblBookings(userID, totalPrice, bookingDate) Values(?,?,?)";
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, dto.getUserID());
            pStm.setDouble(2, dto.getTotalPrice());
            pStm.setString(3, dto.getBookingDate());
            pStm.executeUpdate();
        } catch (Exception e) {
            log4j.error("Error at insertBooking : ", e);
        } finally {
            closeConnection();
        }
    }

    public long getLastBookingID() {
        String sql = "Select MAX(BookingID) from tblBookings";
        long id = 0;
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            rs = pStm.executeQuery();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (Exception e) {
            log4j.error("Eror at getLastBookingID : ", e);
        } finally {
            closeConnection();
        }
        return id;
    }

    public boolean checkQuantity(long roomID, int bookedRoom, int amount) {
        String sql = "select (quantity - ?) as allEmptyRoom "
                + "from tblRooms where RoomID = ?";
        boolean result = true;
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            pStm.setInt(1, bookedRoom);
            pStm.setLong(2, roomID);
            rs = pStm.executeQuery();
            if (rs.next()) {
                if (amount > rs.getInt("allEmptyRoom")) {
                    result = false;
                }
            }
        } catch (Exception e) {
            log4j.error("Error at getEmptyRoom : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getBookedRoom(long roomID, String today, String checkIn, String checkOut) {
        String sql = "select sum(Amount) as sumAmount from tblBookingDetails where roomID = ? and CheckInDate >= ? and "
                + "((? between CheckInDate and CheckOutDate) or (CheckInDate between ? and ?))";
        int result = 0;
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            pStm.setLong(1, roomID);
            pStm.setString(2, today);
            pStm.setString(3, checkIn);
            pStm.setString(4, checkIn);
            pStm.setString(5, checkOut);
            rs = pStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("sumAmount");
            }
        } catch (Exception e) {
            log4j.error("Error at getEmptyRoom : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    //    public boolean checkEmptyRoom(long roomID, int amount, String today, String checkIn, String checkOut){
    //        String sql = "select (quantity - (select sum(Amount) from tblBookingDetails where roomID = ? and CheckOutDate < ? and "
    //                                        + "((? between CheckInDate and CheckOutDate) or (CheckInDate between ? and ?)))) as allEmptyRoom "
    //                + "from tblRooms where RoomID = ?";
    //        try {
    //            cn = db.getConnection();
    //            pStm = cn.prepareStatement(sql);
    //            pStm.setLong(1, roomID);
    //            pStm.setString(2, today);
    //            pStm.setString(3, checkIn);
    //            pStm.setString(4, checkIn);
    //            pStm.setString(5, checkOut);
    //            pStm.setLong(6, roomID);
    //            rs = pStm.executeQuery();
    //            if(rs.next()){
    //                if(amount > rs.getInt("allEmptyRoom")){
    //                    return false;
    //                }
    //            }
    //        } catch (Exception e) {
    //            log4j.error("Error at getEmptyRoom : ",e);
    //        } finally{
    //            closeConnection();
    //        }
    //        return true;
    //    }
}

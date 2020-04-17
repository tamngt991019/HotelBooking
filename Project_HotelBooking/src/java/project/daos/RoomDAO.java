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
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import project.dtos.RoomDTO;
import project.utils.DBConnection;

/**
 *
 * @author Kami.Sureiya
 */
public class RoomDAO {

    private final Logger log4j = Logger.getLogger(RoomDAO.class);
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

    public List<RoomDTO> searchByHotelName(String keyword, int from, int to) {
        List<RoomDTO> data = new ArrayList<>();
        String sql = "SELECT roomID, hotelID, typeID, quantity, unitPrice "
                + "FROM ( SELECT roomID, hotelID, typeID, quantity, unitPrice, statusNow, ROW_NUMBER() OVER (ORDER BY roomID) AS rowNum "
                + "FROM tblRooms Where statusNow = 1 and quantity > 0 and hotelID IN(Select hotelID From tblHotels Where hotelName like ?)) AS pages "
                + "WHERE pages.rowNum BETWEEN ? AND ? ";
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, "%" + keyword + "%");
                pStm.setInt(2, from);
                pStm.setInt(3, to);
                rs = pStm.executeQuery();
                while (rs.next()) {
                    long roomID = rs.getLong("roomID");
                    long hotelID = rs.getLong("hotelID");
                    String typeID = rs.getString("typeID");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("unitPrice");
                    data.add(new RoomDTO(roomID, hotelID, typeID, quantity, price));
                }
            }
        } catch (Exception e) {
            log4j.error("Error at searchByHotelName : ", e);
        } finally {
            closeConnection();
        }
        return data;
    }

    public boolean deleteRoom(long roomID) {
        String sql = "Update tblRooms Set statusNow = 0 Where roomID = ?";
        boolean result = false;
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setLong(1, roomID);
                int n = pStm.executeUpdate();
                if (n > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            log4j.error("Error at deleteRoom : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public void updateRoom(RoomDTO dto) {
        String sql = "Update tblRooms Set typeID = ?, quantity = ?, unitPrice = ? Where roomID = ?";
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, dto.getTypeID());
                pStm.setInt(2, dto.getQuantity());
                pStm.setDouble(3, dto.getPrice());
                pStm.setLong(4, dto.getRoomID());
                pStm.executeUpdate();
            }
        } catch (Exception e) {
            log4j.error("Error at updateRoom : ", e);
        } finally {
            closeConnection();
        }
    }

    public int getNoOfActiveRoomsByHotelName(String keyword) {
        String sql = "select count(roomID) from tblRooms "
                + "where statusNow = 1 and hotelID IN(Select hotelID From tblHotels Where hotelName like ?)";
        int result = 0;
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, "%" + keyword + "%");
                rs = pStm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            log4j.error("Error at getNoOfActiveRoomsByHotelName : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkRoomTypeOfHotel(RoomDTO dto) {
        String sql = "select roomID from tblRooms where hotelID = ? and typeID = ?";
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setLong(1, dto.getHotelID());
                pStm.setString(2, dto.getTypeID());
                rs = pStm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            log4j.error("Error at checkRoomTypeOfHotel : ", e);
        } finally {
            closeConnection();
        }
        return false;
    }

    public void insertRoom(RoomDTO dto) {
        String sql = "insert into tblRooms(hotelID,typeID,quantity, unitPrice, statusNow) values (?,?,?,?,1)";
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setLong(1, dto.getHotelID());
                pStm.setString(2, dto.getTypeID());
                pStm.setInt(3, dto.getQuantity());
                pStm.setDouble(4, dto.getPrice());
                pStm.executeUpdate();
            }
        } catch (Exception e) {
            log4j.error("Error at insertRoom : ", e);
        } finally {
            closeConnection();
        }
    }

    public List<RoomDTO> searchByDate(String address, int amount, int from, int to) {
        List<RoomDTO> data = new ArrayList<>();
        String sql = "SELECT roomID, hotelID, typeID, quantity, unitPrice "
                + "FROM ( SELECT roomID, hotelID, typeID, quantity, unitPrice, statusNow, ROW_NUMBER() OVER (ORDER BY roomID) AS rowNum "
                + "FROM tblRooms Where statusNow = 1 and quantity > 0 and "
                + "hotelID IN(select hotelID from tblRooms "
                + "where hotelID IN(Select hotelID from tblHotels "
                + "where address like ?) "
                + "group by hotelID having SUM(quantity) >= ?)) AS pages "
                + "WHERE pages.rowNum BETWEEN ? AND ?";
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, "%" + address + "%");
            pStm.setInt(2, amount);
            pStm.setInt(3, from);
            pStm.setInt(4, to);
            rs = pStm.executeQuery();
            while (rs.next()) {
                long roomID = rs.getLong(1);
                long hotelID = rs.getLong(2);
                String typeRoom = rs.getString(3);
                int quantity = rs.getInt(4);
                double price = rs.getDouble(5);
                data.add(new RoomDTO(roomID, hotelID, typeRoom, quantity, price));
            }
        } catch (Exception e) {
            log4j.error("Error at searchByDate : ", e);
        } finally {
            closeConnection();
        }
        return data;
    }

    public int getNoOfActiveRoomsByDate(String address, int quantity) {
        String sql = "select count(roomID) from tblRooms "
                + "where statusNow = 1 and hotelID IN(select hotelID from tblRooms where hotelID "
                + "IN(Select hotelID from tblHotels where address like ?)  "
                + "group by hotelID having SUM(quantity) >= ?)";
        int result = 0;
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, "%" + address + "%");
                pStm.setInt(2, quantity);
                rs = pStm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            log4j.error("Error at getNoOfActiveRoomsByDate : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean isRoomExisted(long roomID){
        String sql = "Select roomID from tblRooms where roomID = ?";
        boolean result = false;
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            pStm.setLong(1, roomID);
            rs = pStm.executeQuery();
            if(rs.next()){
                result = true;
            }
        } catch (Exception e) {
            log4j.error("Error at isRoomExisted : ",e);
        }finally{
            closeConnection();
        }
        return result;
    }
}

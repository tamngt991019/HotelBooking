/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.daos;

import project.dtos.HotelDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import project.utils.DBConnection;

/**
 *
 * @author Kami.Sureiya
 */
public class HotelDAO {
    private final Logger log4j = Logger.getLogger(HotelDAO.class);
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
    
    public List<HotelDTO> loadHotel(){
        List<HotelDTO> data = new ArrayList<>();
        String sql = "Select hotelID, hotelName, address From tblHotels";
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            rs = pStm.executeQuery();
            while(rs.next()){
                long hotelID = rs.getLong(1);
                String hotelname = rs.getString(2);
                String address = rs.getString(3);
                data.add(new HotelDTO(hotelID, hotelname, address));
            }
        } catch (Exception e) {
            log4j.error("Error at loadHotel : ",e);
        } finally {
            closeConnection();
        }
        return data;
    }
    
}

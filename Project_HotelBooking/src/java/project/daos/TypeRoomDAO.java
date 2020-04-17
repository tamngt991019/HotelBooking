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
import project.dtos.TypeRoomDTO;
import project.utils.DBConnection;

/**
 *
 * @author Kami.Sureiya
 */
public class TypeRoomDAO {

    private final Logger log4j = Logger.getLogger(TypeRoomDAO.class);
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
//            e.printStackTrace();
        }
    }

    public List<TypeRoomDTO> loadTypeRoom() {
        List<TypeRoomDTO> data = new ArrayList<>();
        String sql = "Select typeID, typeName From tblTypeRooms";
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            rs = pStm.executeQuery();
            while (rs.next()) {
                String typeID = rs.getString(1);
                String typename = rs.getString(2);
                data.add(new TypeRoomDTO(typeID, typename));
            }
        } catch (Exception e) {
            log4j.error("Error at loadTypeRoom : ", e);
        } finally {
            closeConnection();
        }
        return data;
    }
}

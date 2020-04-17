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
import project.dtos.RoleDTO;
import project.utils.DBConnection;

/**
 *
 * @author Kami.Sureiya
 */
public class RoleDAO {
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
    
    public List<RoleDTO> loadRole(){
        String sql =  "select roleID, roleName from tblRoles";
        List<RoleDTO> data = new ArrayList<>();
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            rs = pStm.executeQuery();
            while(rs.next()){
                String roleID = rs.getString(1);
                String roleName = rs.getString(2);
                data.add(new RoleDTO(roleID, roleName));
            }
        } catch (Exception e) {
            log4j.error("Error at loadRole : ",e);
        }finally{
            closeConnection();
        }
        return data;
    }
}

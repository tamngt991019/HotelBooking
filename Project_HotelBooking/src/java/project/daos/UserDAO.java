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
import project.dtos.UserDTO;
import project.utils.DBConnection;

/**
 *
 * @author Kami.Sureiya
 */
public class UserDAO {

    private final Logger log4j = Logger.getLogger(UserDAO.class);
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

    public UserDTO checkLogin(UserDTO dto) {
        UserDTO result = null;
        String sql = "Select userName, roleID from tblUsers "
                + "where userID = ? and password = ?";
        try {
            cn = db.getConnection();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, dto.getUserID());
            pStm.setString(2, dto.getPassword());
            rs = pStm.executeQuery();
            if (rs.next()) {
                result = new UserDTO(dto.getUserID(), rs.getString("userName"), "", rs.getString("roleID"));
            }
        } catch (Exception e) {
            log4j.error("Error at checkLogin : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public void create(UserDTO user) {
        try {
            cn = db.getConnection();
            if (cn != null) {
                String sql = "Insert into tblUsers(userID,userName,password,roleID) Values(?,?,?,?)";
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, user.getUserID());
                pStm.setString(2, user.getUserName());
                pStm.setString(3, user.getPassword());
                pStm.setString(4, user.getRoleID());
                pStm.executeUpdate();
            }
        } catch (Exception e) {
            log4j.error("Error at create : ", e);
        } finally {
            closeConnection();
        }
    }

    public boolean checkExist(String userID) {
        String sql = "Select userID from tblUsers where userID= ?";
        boolean result = false;
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, userID);
                rs = pStm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            log4j.error("Error at checkExist : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserDTO> searchByUserName(String keyword, int from, int to) {
        List<UserDTO> data = new ArrayList<>();
        String sql = "SELECT userID, userName, roleID "
                + "FROM ( SELECT userID, userName, roleID, statusNow, ROW_NUMBER() OVER (ORDER BY userID) AS rowNum "
                + "FROM tblUsers Where statusNow = 1 and userName like ?) AS pages "
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
                    String userID = rs.getString(1);
                    String userName = rs.getString(2);
                    String roleID = rs.getString(3);
                    data.add(new UserDTO(userID, userName, "", roleID));
                }
            }
        } catch (Exception e) {
            log4j.error("Error at searchByUserName : ", e);
        } finally {
            closeConnection();
        }
        return data;
    }

    public int getNoOfActiveUser(String keyword) {
        String sql = "select count(userID) from tblUsers "
                + "where statusNow = 1 and userName like ?";
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
            log4j.error("Error at getNoOfActiveUser : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteUser(String userID) {
        String sql = "Update tblUsers Set statusNow = 0 Where userID = ?";
        boolean result = false;
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, userID);
                int n = pStm.executeUpdate();
                if (n > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            log4j.error("Error at deleteUser : ", e);
        } finally {
            closeConnection();
        }
        return result;
    }

    public void updateUser(UserDTO dto) {
        String sql = "Update tblUsers Set userName = ?, roleID = ? Where userID = ?";
        try {
            cn = db.getConnection();
            if (cn != null) {
                pStm = cn.prepareStatement(sql);
                pStm.setString(1, dto.getUserName());
                pStm.setString(2, dto.getRoleID());
                pStm.setString(3, dto.getUserID());
                pStm.executeUpdate();
            }
        } catch (Exception e) {
            log4j.error("Error at updateUser : ", e);
        } finally {
            closeConnection();
        }
    }
}

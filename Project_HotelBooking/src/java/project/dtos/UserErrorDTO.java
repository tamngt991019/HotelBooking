/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dtos;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class UserErrorDTO implements Serializable{
    private String userIDError;
    private String userNameError;
    private String passwordError;
    private String rePasswordError;
    private String roleIDError;

    public UserErrorDTO() {
    }

    public String getUserIDError() {
        return userIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRePasswordError() {
        return rePasswordError;
    }
    
    public String getRoleIDError() {
        return roleIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRePasswordError(String rePasswordError) {
        this.rePasswordError = rePasswordError;
    }
   
    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }
    
    
}

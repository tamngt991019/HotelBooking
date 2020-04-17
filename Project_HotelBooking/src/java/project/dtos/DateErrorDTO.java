/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dtos;

import java.io.Serializable;

/**
 *
 * @author Kami.Sureiya
 */
public class DateErrorDTO implements  Serializable{
    private String checkInDateError;
    private String checkOutDateError;

    public DateErrorDTO() {
    }

    public DateErrorDTO(String checkInDateError, String checkOutDateError) {
        this.checkInDateError = checkInDateError;
        this.checkOutDateError = checkOutDateError;
    }

    public String getCheckInDateError() {
        return checkInDateError;
    }

    public void setCheckInDateError(String checkInDateError) {
        this.checkInDateError = checkInDateError;
    }

    public String getCheckOutDateError() {
        return checkOutDateError;
    }

    public void setCheckOutDateError(String checkOutDateError) {
        this.checkOutDateError = checkOutDateError;
    }
    
}

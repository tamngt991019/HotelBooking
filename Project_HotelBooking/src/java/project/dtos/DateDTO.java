/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dtos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kami.Sureiya
 */
public class DateDTO implements Serializable{

    private final String formatDate = "yyyy-MM-dd";
    private String checkInDate;
    private String checkOutDate;

    public DateDTO() {
    }

    public DateDTO(String checkInDate, String checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void getDefaultDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        checkInDate = sdf.format(date);
        checkOutDate = sdf.format(getNextDate(date));
    }

    private Date getNextDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public boolean isValidDate(DateErrorDTO dateError, String checkIn, String checkOut) {
        
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        sdf.setLenient(false);
        boolean check = true;
        
        if (checkIn == null || checkIn.isEmpty()) {
            dateError.setCheckInDateError("Check-in date cannot be blank !");
            check = false;
        }
        try {
            Date date1 = (Date) sdf.parse(checkIn);
            if (checkIn.compareTo(sdf.format(new Date())) == -1) {
                dateError.setCheckInDateError("Check-in date must be greater or equals today !");
                check = false;
            }
        } catch (ParseException e) {
            dateError.setCheckInDateError("Check-in date is invalid !");
            check = false;
        }
        
        if (checkOut == null || checkOut.isEmpty()) {
            dateError.setCheckOutDateError("Check-out date cannot be blank !");
            check = false;
        }
        try {
            Date date2 = (Date) sdf.parse(checkOut);
            if (checkOut.compareTo(checkIn) == -1 || checkOut.compareTo(checkIn) == 0) {
                dateError.setCheckOutDateError("Check-out date must be greater than check-in date !");
                check = false;
            }
        } catch (ParseException e) {
            dateError.setCheckOutDateError("Check-out date is invalid !");
            check = false;
        }
        checkInDate = checkIn;
        checkOutDate = checkOut;
        return check;
    }

    public long getNoOfDays() {
        long result = 0;
        try {
            Date date1 = new SimpleDateFormat(formatDate).parse(checkInDate);
            Date date2 = new SimpleDateFormat(formatDate).parse(checkOutDate);
            result = (date2.getTime() - date1.getTime()) / (24 * 3600 * 1000);
            if(result < 1){
                result = -1;
            }
        } catch (ParseException ex) {
            result = -2;
        }
        return result;
    }
}

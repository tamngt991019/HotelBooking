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
public class BookingDTO implements Serializable{
    private long bookingID;
    private String userID;
    private double totalPrice;
    private String bookingDate;

    public BookingDTO() {
    }

    public BookingDTO(String userID, double totalPrice, String bookingDate) {
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
    }

    public BookingDTO(long bookingID, String userID, double totalPrice, String bookingDate) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
    }

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    
}

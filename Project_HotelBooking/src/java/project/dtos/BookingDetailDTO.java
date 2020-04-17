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
public class BookingDetailDTO implements Serializable{
    private long bookingID;
    private long roomID;
    private int amount;
    private double price;
    private String checkInDate;
    private String checkOutDate;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(long bookingID, long roomID, int amount, double price, String checkInDate, String checkOutDate) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.amount = amount;
        this.price = price;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    
}

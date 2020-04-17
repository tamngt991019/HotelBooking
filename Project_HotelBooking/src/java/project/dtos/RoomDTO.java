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
public class RoomDTO implements Serializable{
    private long roomID;
    private long hotelID;
    private String typeID;
    private int quantity;
    private double price;
    private boolean statusNow;

    public RoomDTO() {
    }

    public RoomDTO(long roomID, long hotelID, String typeID, int quantity, double price) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.typeID = typeID;
        this.quantity = quantity;
        this.price = price;
    }

    public RoomDTO(long roomID, long hotelID, String typeID, int quantity, double price, boolean statusNow) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.typeID = typeID;
        this.quantity = quantity;
        this.price = price;
        this.statusNow = statusNow;
    }

    
    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public long getHotelID() {
        return hotelID;
    }

    public void setHotelID(long hotelID) {
        this.hotelID = hotelID;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatusNow() {
        return statusNow;
    }

    public void setStatusNow(boolean statusNow) {
        this.statusNow = statusNow;
    }
    
    
    
}

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
public class HotelDTO implements Serializable{

    private long hotelID;
    private String hotelName;
    private String address;

    public HotelDTO() {
    }

    public HotelDTO(long hotelID, String hotelName, String address) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.address = address;
    }
    
    public long getHotelID() {
        return hotelID;
    }

    public void setHotelID(long hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    } 
}

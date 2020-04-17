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
public class RoomErrorDTO implements Serializable {

    private String roomIDError;
    private String hotelIDError;
    private String typeRoomError;
    private String quantityError;
    private String priceError;

    public RoomErrorDTO() {
    }

    public RoomErrorDTO(String roomIDError, String hotelIDError, String typeRoomError, String quantityError, String priceError) {
        this.roomIDError = roomIDError;
        this.hotelIDError = hotelIDError;
        this.typeRoomError = typeRoomError;
        this.quantityError = quantityError;
        this.priceError = priceError;
    }

    public String getRoomIDError() {
        return roomIDError;
    }

    public void setRoomIDError(String roomIDError) {
        this.roomIDError = roomIDError;
    }

    public String getHotelIDError() {
        return hotelIDError;
    }

    public void setHotelIDError(String hotelIDError) {
        this.hotelIDError = hotelIDError;
    }

    public String getTypeRoomError() {
        return typeRoomError;
    }

    public void setTypeRoomError(String typeRoomError) {
        this.typeRoomError = typeRoomError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kami.Sureiya
 */
public class CartDTO implements Serializable{
    private Map<Long, RoomDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<Long, RoomDTO> cart) {
        this.cart = cart;
    }

    public Map<Long, RoomDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Long, RoomDTO> cart) {
        this.cart = cart;
    }
    
    public void add(RoomDTO dto){
        if(this.cart == null){
            this.cart = new HashMap<>();
        }
        if(this.cart.containsKey(dto.getRoomID())){
            int quantity = this.cart.get(dto.getRoomID()).getQuantity();
            dto.setQuantity(quantity + 1);
        }
        cart.put(dto.getRoomID(), dto);
    }
    
    public void delete(Long id){
        if(this.cart == null){
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    
    public void update(RoomDTO dto){
        if(this.cart == null){
            return;
        }
        if(this.cart.containsKey(dto.getRoomID())){
            this.cart.replace(dto.getRoomID(), dto);
        }
    }
}

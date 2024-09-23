package com.java.web.LaptopShop.domain.DTO;

import com.java.web.LaptopShop.domain.Cart;

public class ReceiverDTO {
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;

    public Cart addReceiverInform(Cart cart) {
        cart.setReceiverAddress(receiverAddress);
        cart.setReceiverName(receiverName);
        cart.setReceiverPhone(receiverPhone);
        return cart;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

}

package com.example.models.request;

import java.util.List;

import com.example.demo.purchase.Purchase;

public class PostPurchaseRequest{
    private Long roomId;
    private Purchase purchase;
    private List<Long> usersId;

    public Purchase getPurchase() {
        return this.purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public List<Long> getUsersId() {
        return this.usersId;
    }

    public Long getRoomId() {
        return this.roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
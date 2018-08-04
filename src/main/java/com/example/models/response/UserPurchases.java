package com.example.models.response;

import java.util.Date;

import com.example.demo.purchase.Purchase;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPurchases{
    
    private User user;
    private Purchase purchase;

    public UserPurchases(User user, Purchase purchase){
        
            this.user = user;
            this.purchase = purchase; 
        // this.purchase = purchase;
    }

    public UserPurchases(){}

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

}

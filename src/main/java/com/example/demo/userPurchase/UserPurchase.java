package com.example.demo.userPurchase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.demo.purchase.Purchase;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="user_purchase")
public class UserPurchase{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="purchase_id")
    private Purchase purchase;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonProperty(access=Access.WRITE_ONLY)
    private User user;

    @NotNull
    private float total;
    private boolean payed = false;

    @Column(name="is_owner")
    private boolean isOwner;

    public UserPurchase(User user, Purchase purchase, int nUsers, boolean isOwner){
        this.user = user;
        this.purchase = purchase;
        this.total = purchase.getTotal()/nUsers;
        this.isOwner = isOwner;
        if(isOwner)
            this.payed = true;
    }

    public UserPurchase(){}

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public float getTotal() {
        return this.total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    public boolean getIsOwner() {
        return this.isOwner;
    }
    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public boolean getPayed() {
        return this.payed;
    }
    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
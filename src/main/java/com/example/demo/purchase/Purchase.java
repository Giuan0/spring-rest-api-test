package com.example.demo.purchase;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.example.demo.room.Room;
import com.example.demo.userPurchase.UserPurchase;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="purchases")
public class Purchase{

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String description;
    
    private String local;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @OneToMany(fetch = FetchType.LAZY, mappedBy="purchase")
    private Set<UserPurchase> userPurchases = new HashSet<>();
    
    @ManyToOne(
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinColumn(name="room_id")
    @JsonProperty(access=Access.WRITE_ONLY)
    private Room room;

    private float total;
    private boolean payed = false;

    public Purchase(Long id, String description, String local, float total, Date date){
        this.id = id;
        this.description  = description;
        this.local = local;
        this.total = total;
        this.date  = date;
    }
    public Purchase(){}

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocal() {
        return this.local;
    }
    public void setLocal(String local) {
        this.local = local;
    }

    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return this.total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    public boolean getPayed() {
        return this.payed;
    }
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Room getRoom() {
        return this.room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
}
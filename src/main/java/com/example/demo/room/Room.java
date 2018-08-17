package com.example.demo.room;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.purchase.Purchase;
import com.example.demo.user.User;
import com.example.demo.userPurchase.UserPurchase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name="rooms")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "name")

public class Room{

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt = new Date();
    
    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    },mappedBy="rooms")
    private Set<User> users = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="room")
    private Set<Purchase> purchases = new HashSet<>();

    public Room(){}


    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date attribute) {
        this.createdAt = createdAt;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    @JsonIgnore
    public List<User> getUsersNames(){//refactor, look for better implementation
        List<User> users = new ArrayList<User>();
        this.users.forEach(u->{
            users.add(new User(u));
        });

        return users;
    }

    public Set<Purchase> getPurchases() {
        return this.purchases;
    }
    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }
    // public void setUsers(List<User> users) {
    //     this.users = users;
    // }
}
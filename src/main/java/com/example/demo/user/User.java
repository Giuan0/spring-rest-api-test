package com.example.demo.user;

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
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import com.example.demo.room.Room;
import com.example.demo.userPurchase.UserPurchase;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="users")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "name")
public class User{

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;

    @Column(unique=true)
    private String email;

    @NotNull
    @JsonProperty(access=Access.WRITE_ONLY)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt = new Date();

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name="room_users",
        joinColumns={@JoinColumn(name="user_id")},
        inverseJoinColumns={@JoinColumn(name="room_id")}
    )
    // @JsonIgnore
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy="user")
    private Set<UserPurchase> userPurchases = new HashSet<>();

    public User(){}
    
    public User(Long id, String name, String email){
        this.id    = id;
        this.name  = name;
        this.email = email;
    }

    public User(User user){
        this.id = user.getId();
        this.name  = user.getName();
        this.email = user.getEmail();
    }

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

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }
    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
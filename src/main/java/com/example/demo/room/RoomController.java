package com.example.demo.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.models.response.DefaultResponse;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController{

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/rooms")
    public DefaultResponse<List<Room>> getRooms(){
        List<Room> rooms = this.roomRepository.findAll();
        return new DefaultResponse<List<Room>>(rooms, "all rooms", 200);
    }

    @PostMapping("/rooms/{roomId}/join")//improov
    public DefaultResponse joinRoom(
        @RequestBody() User userE, 
        @PathVariable("roomId") Long roomId){

        
        User user = this.userRepository.findByEmailAddress(userE.getEmail());
        this.roomRepository.findById(roomId).map(room->{
            user.getRooms().add(room);
            room.getUsers().add(user);
            return this.userRepository.save(user);
        });
        System.out.println(roomId);
        return new DefaultResponse<>(null, user.getEmail()+" has joined the room", 200);
    }

    @PostMapping("/rooms/{userId}")
    public DefaultResponse postRoom(
        @RequestBody Room room,
        @PathVariable(value="userId") Long userId
        ){
        this.userRepository.findById(userId).map(user->{
            user.getRooms().add(room);
            room.getUsers().add(user);
            // this.roomRepository.save(room);
            return this.userRepository.save(user);
        });
        
        return new DefaultResponse<>(null, "Room created", 200);
    }

    @GetMapping("/rooms/{roomId}")//gambiarra, fix it latter
    public DefaultResponse<Optional<Room>> getRoom(@PathVariable(value="roomId") Long roomId){
        Optional<Room> room = this.roomRepository.findById(roomId);

        return new DefaultResponse<Optional<Room>>(room, "room id "+roomId, 200);
    }

    @GetMapping("/rooms/users/{roomId}")//gambiarra, fix it latter
    public DefaultResponse<List<User>> getUsersRoom(@PathVariable(value="roomId") Long roomId){
        Optional<Room> room = this.roomRepository.findById(roomId);

        return new DefaultResponse<List<User>>(room.get().getUsersNames(), "room id "+roomId, 200);
    }
}
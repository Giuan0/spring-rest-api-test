package com.example.demo.user;

import java.util.List;

import com.example.demo.room.Room;
import com.example.demo.room.RoomRepository;
import com.example.models.response.DefaultResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/users")
    public DefaultResponse<List<User>> getUsers(){
        return new DefaultResponse<List<User>>(userRepository.findAll(), "list users", 200);
    }

    @GetMapping("/users/{userId}")
    public DefaultResponse<User> getUserById(@PathVariable("userId") Long userId){
        User user = this.userRepository.findById(userId).get();
        return new DefaultResponse<User>(user, "get user", 200);
    }

    // @GetMapping("/users/{usersId}/rooms")
    // public List<Room> getUserRooms(@PathVariable(value="userId") Long userId){

    // }

    @PostMapping("/users")
    public DefaultResponse postUser(
        @RequestBody User user
        ){
            userRepository.save(user);
            return new DefaultResponse<>(null, "Registered user!", 200);
    }
}
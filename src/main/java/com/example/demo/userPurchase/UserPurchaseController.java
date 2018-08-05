package com.example.demo.userPurchase;
import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;
import java.util.Optional;

// import com.example.demo.purchase.Purchase;
// import com.example.demo.room.Room;
// import com.example.demo.room.Room;
import com.example.demo.room.RoomRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.models.request.PostPurchaseRequest;
// import com.example.models.response.UserPurchases;
import com.example.models.response.DefaultResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPurchaseController{
    @Autowired
    UserPurchaseRepository userPurchaseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository; 

    // @GetMapping("/user/purchase/")
    // public List<UserPurchase> purchase(){
    //     return this.userPurchaseRepository.findAll();
    // }

    @GetMapping("/users/purchases/{userId}")//refactor, filter informations(user and owner)
    public DefaultResponse<List<UserPurchase>> getAllPurchaseByUserId(
        @PathVariable("userId") Long userId
    ){  
        List<UserPurchase> usersPurchase = this.userPurchaseRepository.findByUserId(userId);
        return new DefaultResponse<List<UserPurchase>>(usersPurchase, "users purchase", 200);
    }

    @PutMapping("/users/purchases/finish/{userId}")//mark as payed
    public DefaultResponse finishPurchase(@PathVariable("userId") Long userId){
        
        userPurchaseRepository.finishOrder(userId, true);
        return new DefaultResponse<>(null, "Purchase finished", 200);
    }

    @PostMapping("/users/purchases/{userId}")//needs to refactor
    public DefaultResponse postPurchase(
        @PathVariable("userId") Long userId,
        @RequestBody PostPurchaseRequest request
        ){
            
            Optional<User> owner = this.userRepository.findById(userId);
            List<User> users = this.userRepository.findAllById(request.getUsersId());
            List<UserPurchase> usersPurchase = new ArrayList<UserPurchase>();
            
            this.roomRepository.findById(request.getRoomId()).map(room->{
                request.getPurchase().setRoom(room);
                // room.getPurchases().add(request.getPurchase());
                return roomRepository.save(room);
            });
            
            
            users.forEach(user->{
                UserPurchase userPurchase = new UserPurchase(
                    user, 
                    request.getPurchase(), 
                    request.getUsersId().size(),
                    owner.get()
                );

                usersPurchase.add(userPurchase);
            });
            
            this.userPurchaseRepository.saveAll(usersPurchase);

        return new DefaultResponse<>(null, "registered purchase", 200);
    }
}
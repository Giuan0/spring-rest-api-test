package com.example.demo.purchase;

import java.util.List;

import com.example.models.response.DefaultResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController{
    @Autowired
    PurchaseRepository purchaseRepository;

    @GetMapping("/purchases/{roomId}")
    public DefaultResponse<List<Purchase>> getPurchases(@PathVariable("roomId") Long roomId){
        List<Purchase> purchases = this.purchaseRepository.findAllByRoomId(roomId);
        return new DefaultResponse<List<Purchase>>(purchases, "all purchases from room "+roomId, 200);
    }

    // @PostMapping("/purchase")
    // public String postPurchase(@RequestBody Purchase purchase){
    //     return "Purchase resgistered";
    // }
}
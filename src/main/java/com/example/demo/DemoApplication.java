package com.example.demo;

import com.example.demo.room.RoomController;
import com.example.demo.user.UserController;
import com.example.demo.userPurchase.UserPurchaseController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses={
	UserController.class,
	RoomController.class,
	DemoApplication.class,
	UserPurchaseController.class
})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

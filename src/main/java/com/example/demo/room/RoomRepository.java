package com.example.demo.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    // @Query("select r from room_users r where r.user_id = ?1")
    // Room findUserRooms(Long userId);
}
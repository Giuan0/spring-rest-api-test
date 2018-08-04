package com.example.demo.purchase;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    @Query("select p from Purchase p where p.room.id = ?1")
    List<Purchase> findAllByRoomId(Long roomId);
}
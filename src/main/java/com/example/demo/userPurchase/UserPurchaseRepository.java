package com.example.demo.userPurchase;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.user.User;
import com.example.models.response.UserPurchases;
import com.example.models.response.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPurchaseRepository extends JpaRepository<UserPurchase, Long>{
    // @Query("select u from User u where u.email = ?1")
    // User findByEmailAddress(String email);
    // @Query("select "
    // +"new com.example.demo.user.User(user.id, user.name, user.email),"
    // +"new com.example.demo.purchase(purchase.id, purchase.name, purchase.local, purchase.total, purchase.date)"
    // +"from UserPurchase up "
    // +"join User as user on (up.user = user.id)"
    // +" join Purchase as purchase on (up.purchase = purchase.id )")

    @Query(
        value=""+
        "SELECT new com.example.models.response.Bill("+
            "up.purchase.id, "+
            "purchase.total, "+
            "purchase.description, "+
            "SUM(CASE WHEN up.owner = up.user THEN 0 ELSE up.total END), "+
            "COUNT(up.purchase), "+
            "up.owner.id = ?1, "+
            "owner.name, "+
            "owner.id, "+
            "SUM(CASE WHEN up.user.id = ?1 AND up.user <> up.owner THEN up.total ELSE 0 END) ) "+
        "FROM UserPurchase up "+
        "JOIN Purchase as purchase ON (up.purchase = purchase.id) "+
        "JOIN User as owner ON (owner.id = up.owner) "+
        "WHERE purchase.room.id = ?2 "+
        "GROUP BY up.purchase, owner.id ")
    List<Bill> bills(Long userId, Long roomId);

    @Query("select "
    +"new com.example.models.response.UserPurchases"
    +"(user, purchase)"
    // +"new com.example.demo.purchase(purchase.id, purchase.name, purchase.local, "
    +"from UserPurchase up "
    +"join User as user on (up.user = user.id)"
    +"join Purchase as purchase on (up.purchase = purchase.id)")
    // @Query(
    //     value="select new com.example.models.response.UserPurchases(user.name) from UserPurchase up join User as user on (up.user = user.id)")
    List<UserPurchases> findAllUserPurchases();

    @Query("SELECT new com.example.demo.userPurchase.UserPurchase(up) FROM UserPurchase up WHERE (up.user.id = ?1 OR up.owner.id = ?1) AND up.user.id <> up.owner.id")
    List<UserPurchase> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("update UserPurchase set payed = ?2 where id = ?1")
    void finishOrder(Long id, boolean payed);
}

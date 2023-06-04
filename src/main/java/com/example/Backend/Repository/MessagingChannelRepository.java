package com.example.Backend.Repository;

import com.example.Backend.Entity.Appointment;
import com.example.Backend.Entity.Messaging_chanel;
import com.example.Backend.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagingChannelRepository extends JpaRepository<Messaging_chanel, Integer> {

    @Query("SELECT c FROM Messaging_chanel c WHERE c.user.employee_id = :userId OR c.user1.employee_id = :userId")
    List<Messaging_chanel> findByUserIdOrUser1Id(@Param("userId") Long userId);
    @Query("SELECT c FROM Messaging_chanel c WHERE  c.channel_id = :Channel_id")
    Messaging_chanel findByChannel_id(@Param("Channel_id") Long Channel_id);


    //Messaging_chanel findByChannel_id(Long Channel_id);


}

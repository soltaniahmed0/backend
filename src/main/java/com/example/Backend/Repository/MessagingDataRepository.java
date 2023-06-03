package com.example.Backend.Repository;



import com.example.Backend.Entity.MessageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessagingDataRepository extends JpaRepository<MessageData, Integer> {
    @Query("SELECT m FROM MessageData m WHERE m.channel.channel_id = :channelId")
    List<MessageData> findByChannel_Id(@Param("channelId") Long channelId);


}

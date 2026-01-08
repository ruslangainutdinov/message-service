package com.ruslangainutdinov.message.service.repository;

import com.ruslangainutdinov.message.service.entity.Message;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, UUID> {

  @Query(
        """
            SELECT m FROM Message m
            WHERE (:chatId IS NULL OR m.chatId = :chatId)
            AND (:userId IS NULL OR m.userId = :userId)
            ORDER BY m.messageChatN ASC
        """
  )
  List<Message> findByChatAndUser(
      @Param("chatId") UUID chatId,
      @Param("userId") UUID userId,
      org.springframework.data.domain.Pageable pageable
  );

  @Modifying
  @Query(
        """
            UPDATE Message m
            SET m.payload = :payload, m.version = m.version + 1
            WHERE m.id = :id AND m.version = :expectedVersion
        """
  )
  int updatePayload(
      @Param("id") UUID id,
      @Param("expectedVersion") int expectedVersion,
      @Param("payload") String payload
  );

}

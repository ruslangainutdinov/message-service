package com.ruslangainutdinov.message.service.repository;

import com.ruslangainutdinov.message.service.entity.ChatSequence;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSequenceRepository extends JpaRepository<ChatSequence, UUID> {

  default void createChatSequenceIfNotExists(UUID chatId) {
    findById(chatId).orElseGet(() -> save(new ChatSequence(chatId)));
  }

  @Query(
      value = """
                UPDATE chat_sequence
                SET last_n = last_n + 1
                WHERE chat_id = :chatId
                RETURNING last_n
              """,
      nativeQuery = true
  )
  int incrementAndGetLastN(@Param("chatId") UUID chatId);

}

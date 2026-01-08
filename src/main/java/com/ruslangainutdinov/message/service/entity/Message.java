package com.ruslangainutdinov.message.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "message")
public class Message {

  @Id
  @Column(columnDefinition = "uuid")
  private UUID id;

  @Column(name = "user_id", columnDefinition = "uuid")
  private UUID userId;

  @Column(name = "chat_id", columnDefinition = "uuid")
  private UUID chatId;

  @Column(name = "message_chat_n")
  private int messageChatN;

  private int version;

  @Column(columnDefinition = "text")
  private String payload;
}

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
@Table(name = "chat_sequence")
public class ChatSequence {

  public ChatSequence(UUID chatId) {
    this.chatId = chatId;
    this.lastN = 0;
  }

  @Id
  @Column(name = "chat_id", columnDefinition = "uuid")
  private UUID chatId;

  @Column(name = "last_n", nullable = false)
  private int lastN;
}

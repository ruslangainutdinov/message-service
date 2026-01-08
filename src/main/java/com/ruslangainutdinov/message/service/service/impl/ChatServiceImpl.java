package com.ruslangainutdinov.message.service.service.impl;

import com.ruslangainutdinov.message.service.entity.Message;
import com.ruslangainutdinov.message.service.exception.NotFoundException;
import com.ruslangainutdinov.message.service.repository.MessageRepository;
import com.ruslangainutdinov.message.service.service.ChatSequenceService;
import com.ruslangainutdinov.message.service.service.ChatService;
import jakarta.persistence.OptimisticLockException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

  private final MessageRepository messageRepository;
  private final ChatSequenceService chatSequenceService;

  @Transactional
  public Message createMessage(UUID userId, UUID chatId, String payload) {
    int nextN = chatSequenceService.nextSequence(chatId);
    Message message = new Message(
        UUID.randomUUID(),
        userId,
        chatId,
        nextN,
        1,
        payload
    );

    messageRepository.save(message);
    return message;
  }

  @Transactional
  public Message editMessage(UUID messageId, int expectedVersion, String payload) {
    int updated = messageRepository.updatePayload(messageId, expectedVersion, payload);
    if (updated == 0) {
      throw new OptimisticLockException("Message was already edited");
    }
    return getMessage(messageId);
  }

  @Transactional(readOnly = true)
  public List<Message> fetchMessages(UUID chatId, UUID userId, int page, int size) {
    int offset = page * size;
    return messageRepository.findByChatAndUser(chatId, userId, PageRequest.of(offset / size, size));
  }

  private Message getMessage(UUID id) {
    return messageRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Message was not been found by id: " + id));
  }
}

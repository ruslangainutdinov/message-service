package com.ruslangainutdinov.message.service.service.impl;

import com.ruslangainutdinov.message.service.repository.ChatSequenceRepository;
import com.ruslangainutdinov.message.service.service.ChatSequenceService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ChatSequenceServiceImpl implements ChatSequenceService {

  private final ChatSequenceRepository repository;

  @Transactional
  public int nextSequence(UUID chatId) {
    repository.createChatSequenceIfNotExists(chatId);
    return repository.incrementAndGetLastN(chatId);
  }

}

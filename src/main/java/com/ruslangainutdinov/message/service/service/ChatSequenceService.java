package com.ruslangainutdinov.message.service.service;

import java.util.UUID;

public interface ChatSequenceService {

  /**
   * Returns the next strictly incremented sequence number for a given chat.
   *
   * @param chatId the ID of the chat for which to generate the next sequence number
   * @return the next sequence number for the chat
   */
  int nextSequence(UUID chatId);
}

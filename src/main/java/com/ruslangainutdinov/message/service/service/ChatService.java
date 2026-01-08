package com.ruslangainutdinov.message.service.service;

import com.ruslangainutdinov.message.service.entity.Message;
import java.util.List;
import java.util.UUID;

public interface ChatService {

  /**
   * Creates a new message in a specific chat.
   * <p>
   * The message will be assigned a unique ID and a strictly incremented sequence number
   * per chat.
   * </p>
   *
   * @param userId  the ID of the user sending the message
   * @param chatId  the ID of the chat where the message will be posted
   * @param payload the content of the message
   * @return the created {@link Message} with assigned ID, sequence number, version, and payload
   * @throws IllegalArgumentException if any argument is null
   */
  Message createMessage(UUID userId, UUID chatId, String payload);


  /**
   * Edits an existing message.
   * <p>
   * Uses optimistic versioning to ensure that the message is updated only if the provided
   * {@code expectedVersion} matches the current version of the message. If the version
   * does not match, the update will fail, preventing lost updates from concurrent edits.
   * </p>
   *
   * @param messageId       the ID of the message to edit
   * @param expectedVersion the expected current version of the message for optimistic locking
   * @param payload         the new content of the message
   * @return the updated {@link Message} with the new payload and incremented version
   * @throws jakarta.persistence.OptimisticLockException if the message has been modified by another user
   */
  Message editMessage(UUID messageId, int expectedVersion, String payload);


  /**
   * Fetches a paginated list of messages from a specific chat for a specific user.
   * <p>
   * This method can be used to retrieve chat history. Pagination is controlled via
   * {@code page} (zero-based) and {@code size}. Messages are typically ordered by
   * their sequence number.
   * </p>
   *
   * @param chatId the ID of the chat to fetch messages from
   * @param userId the ID of the user requesting the messages (used for filtering or access control)
   * @param page   the page number (zero-based)
   * @param size   the maximum number of messages to return
   * @return a list of {@link Message} objects for the requested page
   * @throws IllegalArgumentException if page or size is negative
   */
  List<Message> fetchMessages(UUID chatId, UUID userId, int page, int size);
}

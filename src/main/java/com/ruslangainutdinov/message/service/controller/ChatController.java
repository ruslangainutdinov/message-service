package com.ruslangainutdinov.message.service.controller;

import com.ruslangainutdinov.message.service.entity.Message;
import com.ruslangainutdinov.message.service.dto.CreateMessageCmd;
import com.ruslangainutdinov.message.service.dto.EditMessageCmd;
import com.ruslangainutdinov.message.service.dto.FetchMessagesCmd;
import com.ruslangainutdinov.message.service.service.ChatService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

  private final SimpMessagingTemplate messagingTemplate;
  private final ChatService chatService;

  @MessageMapping("/chat.send")
  public void send(@Payload @Valid CreateMessageCmd cmd) {
    log.info("New message arrived: {}", cmd);
    Message message = chatService.createMessage(
        cmd.userId(),
        cmd.chatId(),
        cmd.payload()
    );
    messagingTemplate.convertAndSend("/topic/chat." + cmd.chatId(), message);
  }

  @MessageMapping("/chat.edit")
  public void edit(@Payload @Valid EditMessageCmd cmd) {
    log.info("Message edit command: {}", cmd);
    Message message = chatService.editMessage(
        cmd.messageId(),
        cmd.version(),
        cmd.payload()
    );
    messagingTemplate.convertAndSend("/topic/chat." + message.getChatId(), message);
  }

  @MessageMapping("/chat.history")
  public void history(@Payload @Valid FetchMessagesCmd cmd) {
    // I believe this method should be implemented via @SentToUser & return,
    // but since there is no user context, it is implemented via such workaround:
    // client should SUBSCRIBE to /queue/history-" + cmd.userId()
    log.info("Obtain history command: {}", cmd);

    List<Message> msgs = chatService.fetchMessages(
        cmd.chatId(),
        cmd.userId(),
        cmd.page(),
        cmd.size()
    );

    messagingTemplate.convertAndSend(
        "/queue/history-" + cmd.userId(),
        msgs
    );
  }
}

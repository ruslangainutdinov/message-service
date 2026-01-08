package com.ruslangainutdinov.message.service.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class WebSocketExceptionHandler {

  @MessageExceptionHandler
  public void handle(Exception e, SimpMessageHeaderAccessor headerAccessor) {
    headerAccessor.getSessionAttributes().put("error", e.getMessage());
  }
}

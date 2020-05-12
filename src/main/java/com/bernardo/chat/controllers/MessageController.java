package com.bernardo.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.chat.dto.MessageCommand;
import com.bernardo.chat.services.MessageService;

@RestController
public class MessageController {
	
	private MessageService messageService;
	
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@MessageMapping("/message")
	public void broadcastNews(@Payload MessageCommand message) {
		messageService.receiveMessage(message);
	}
}

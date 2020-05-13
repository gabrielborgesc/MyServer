package com.chat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chat.dto.ChatInfoCommand;
import com.chat.dto.MessageCommand;
import com.chat.services.ChatService;

@RestController
public class ChatController {
	
	private ChatService chatService;
	
	@Autowired
	public ChatController(ChatService chatService) 	{
		this.chatService = chatService;
	}
	
	@RequestMapping(value = "/chatInfo", method = RequestMethod.GET)
	public ChatInfoCommand getChatInfo() {
		return chatService.getChatInfo();
	}
	
	@RequestMapping(value="/messages", method = RequestMethod.GET)
	public List<MessageCommand> retrieveChatMessages() {
		return chatService.retrieveChatMessages();
	}

}

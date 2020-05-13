package com.chat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.dto.ChatListCommand;
import com.chat.dto.ChatSelectionCommand;
import com.chat.services.ChatSelectorService;

@RestController
public class ChatSelectorController {

	private ChatSelectorService chatSelectorService;
	
	@Autowired
	public ChatSelectorController(ChatSelectorService chatSelectorService) {
		this.chatSelectorService = chatSelectorService;
	}
	
	@RequestMapping(value = "/chats", method = RequestMethod.GET)
	public List<ChatListCommand> chats() {
		return this.chatSelectorService.chats();
	}

	@RequestMapping(value = "/selectChat", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	public String selectChat(@RequestBody ChatSelectionCommand chatSelectionCommand) {
		return String.valueOf(chatSelectorService.selectChat(chatSelectionCommand));
	}
	
}

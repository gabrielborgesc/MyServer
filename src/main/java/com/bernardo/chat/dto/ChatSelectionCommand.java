package com.bernardo.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatSelectionCommand {
	private Integer chatId;
	
	public ChatSelectionCommand(@JsonProperty("chatId") Integer chatId) {
		this.chatId = chatId;
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	
}

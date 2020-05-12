package com.bernardo.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatListCommand {
	private Integer id;
	private String name;
	private String lastMessage;
	
	public ChatListCommand(@JsonProperty("id") Integer id,
						   @JsonProperty("name") String name,
						   @JsonProperty("lastMessage") String lastMessage) {
		this.id = id;
		this.name = name;
		this.lastMessage = lastMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}
}

package com.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatInfoCommand {
	private Integer userId;
	private String username;
	private Integer roomId;
	private String roomName;
	
	public ChatInfoCommand(@JsonProperty("userId") Integer userId,
						   @JsonProperty("roomId") Integer roomId,
						   @JsonProperty("username") String username,
						   @JsonProperty("roomName") String roomName) {
		this.userId = userId;
		this.roomId = roomId;
		this.username = username;
		this.roomName = roomName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	
}

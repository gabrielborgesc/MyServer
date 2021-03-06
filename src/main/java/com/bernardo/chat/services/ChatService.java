package com.bernardo.chat.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardo.chat.beans.SessionBean;
import com.bernardo.chat.domain.Message;
import com.bernardo.chat.dto.ChatInfoCommand;
import com.bernardo.chat.dto.MessageCommand;
import com.bernardo.chat.repositories.MessageRepository;

@Service
public class ChatService {
	private SessionBean sessionBean;
	private MessageRepository messageRepository;
	
	@Autowired
	public ChatService(SessionBean sessionBean, MessageRepository messageRepository) {
		this.sessionBean = sessionBean;
		this.messageRepository = messageRepository;
	}
	
	public ChatInfoCommand getChatInfo() {
		ChatInfoCommand chatInfo = new ChatInfoCommand( sessionBean.getCurrentUser().getId(), 
														sessionBean.getCurrentRoom().getId(), 
														sessionBean.getUsername(), 
														sessionBean.getCurrentRoom().getName());
		return chatInfo;
	}
	
	public List<MessageCommand> retrieveChatMessages() {
		List<MessageCommand> result = new ArrayList<MessageCommand>();
		List<Message> messages = messageRepository.findAllByRoomIdOrderByCreatedDate(sessionBean.getCurrentRoom().getId()); 
		for(Message message: messages)
			result.add(convert(message));
		
		return result;
	}

	private MessageCommand convert(Message message) {
		return new MessageCommand(message.getText(), message.getUser().getUsername(), message.getUser().getId(), message.getRoom().getId());
	}
}

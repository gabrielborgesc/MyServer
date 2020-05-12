package com.bernardo.chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.bernardo.chat.domain.Message;
import com.bernardo.chat.dto.MessageCommand;
import com.bernardo.chat.repositories.MessageRepository;
import com.bernardo.chat.repositories.RoomRepository;
import com.bernardo.chat.repositories.UserRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private SimpMessagingTemplate simpMessagingTemplate;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
//    private SessionBean sessionBean;

    @Autowired
    public MessageService(MessageRepository messageRepository,
    					  SimpMessagingTemplate simpMessagingTemplate,
    					  RoomRepository roomRepository,
    					  UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public List<Message> findAllByRoomIdOrderByCreatedDate(Integer roomId) {
        return this.messageRepository.findAllByRoomIdOrderByCreatedDate(roomId);
    }
    
    public void receiveMessage(MessageCommand message) {
    	messageRepository.save(convert(message));
    	this.simpMessagingTemplate.convertAndSend("/topic/" + message.getRoomId().toString(), message);
    }

	private Message convert(MessageCommand message) {
		Message newMessage = new Message();
		newMessage.setText(message.getContent());
		newMessage.setRoom(roomRepository.findById(message.getRoomId()).get());
		newMessage.setUser(userRepository.findById(message.getUserId()).get());
		return newMessage;
	}
}

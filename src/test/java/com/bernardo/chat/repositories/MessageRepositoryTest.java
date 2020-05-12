package com.bernardo.chat.repositories;

import com.bernardo.chat.domain.Message;
import com.bernardo.chat.domain.Room;
import com.bernardo.chat.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRepositoryTest {

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoomRepository roomRepository;

  // @Test
  public void findAllTest() {
    Iterable<Message> messages = messageRepository.findAll();
    for (Message message : messages) {
      System.out.println(message.getText());
      System.out.println();
    }
  }

  public void insert(String text, Integer UserId, Integer RoomId) {

    Optional<User> user = userRepository.findById(UserId);
    Optional<Room> room = roomRepository.findById(RoomId);

    if (user.isPresent() && room.isPresent()) {

      Message message = new Message();
      message.setUser(user.get());
      message.setRoom(room.get());
      message.setText(text);

      messageRepository.save(message);

    } else {
      System.out.println("Usuario ou sala inexistentes");
      System.out.println();
    }
  }

  @Test
  public void test() {

    List<Message> list = messageRepository.findAllByRoomIdOrderByCreatedDate(999999);

    for (Message message : list) {
      System.out.println(message.getText() + "  " + message.getCreatedDate());
      System.out.println();
    }
  }
}

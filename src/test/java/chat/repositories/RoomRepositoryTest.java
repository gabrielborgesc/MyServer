package chat.repositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chat.domain.Room;
import com.chat.repositories.RoomRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomRepositoryTest {
  @Autowired
  private RoomRepository roomRepository;

  // @Test
  public void findAllTest() {
    Iterable<Room> rooms = roomRepository.findAll();
    for (Room room : rooms) {
      System.out.println(room.getName());
    }
  }

  // @Test
  public void findById() {
    Optional<Room> optional = roomRepository.findById(100);

    if (optional.isPresent()) {
      Room room = optional.get();

      System.out.println(room.getName());
    } else {
      System.out.println("Null");
      System.out.println();
    }
  }

  // @Test
  public void findByRoomName() {
    Room room = roomRepository.findByName("chat1");
    System.out.print(room.getId());
    System.out.println();
  }

  // @Test
  public void insert(Room newroom) {

    Room room = roomRepository.findByName(newroom.getName());
    if (room == null) {
      roomRepository.save(newroom);
    }
  }

  // @Test
  public void delete(String roomname) {
    Room room = roomRepository.findByName(roomname);
    if (room != null) {
      roomRepository.deleteById(room.getId());
    }
  }

  @Test
  public void test() {

    Room room = new Room();
    room.setName("chat2");
    // insert(room);
    delete("chat2");
  }
}

package com.bernardo.chat.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.chat.domain.Room;
import com.bernardo.chat.dto.CreateUserCommand;
import com.bernardo.chat.dto.GetUserRoomsCommand;
import com.bernardo.chat.dto.RemoveUserCommand;
import com.bernardo.chat.dto.UpdateUserEmailCommand;
import com.bernardo.chat.dto.UpdateUserPasswordCommand;
import com.bernardo.chat.dto.UserCommand;
import com.bernardo.chat.services.UserService;

@RestController
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = "text/plain")
  @ResponseBody
  public String createUser(@RequestBody CreateUserCommand command) {
    Boolean created = this.userService.create(command);
    return String.valueOf(created);
  }

  @RequestMapping(value = "/removeUser", method = RequestMethod.POST, produces = "text/plain")
  @ResponseBody
  public String removeUser(@RequestBody RemoveUserCommand command) {
    Boolean deleted = this.userService.delete(command);
    return String.valueOf(deleted);
  }

  @RequestMapping(value = "/updateUserEmail", method = RequestMethod.POST, produces = "text/plain")
  @ResponseBody
  public String updateEmail(@RequestBody UpdateUserEmailCommand command) {
    Boolean updated = this.userService.updateEmail(command);
    return String.valueOf(updated);
  }

  @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST, produces = "text/plain")
  @ResponseBody
  public String updatePassword(@RequestBody UpdateUserPasswordCommand command) {
    Boolean updated = this.userService.updatePassword(command);
    return String.valueOf(updated);
  }

  @RequestMapping(value = "/getUserRooms", method = RequestMethod.GET)
  public Set<Room> getUserRooms(@RequestBody GetUserRoomsCommand command) {
    return this.userService.getRooms(command);
  }
  
  @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
  public List<UserCommand> getUserList() {
    return this.userService.findAll();
  }
}

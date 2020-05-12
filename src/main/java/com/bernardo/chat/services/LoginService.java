package com.bernardo.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bernardo.chat.beans.SessionBean;
import com.bernardo.chat.domain.Type;
import com.bernardo.chat.domain.User;
import com.bernardo.chat.dto.LoginCommand;

@Service
public class LoginService {

  private SessionBean sessionBean;
  private UserService userService;

  @Autowired
  public LoginService(SessionBean sessionBean, UserService userService) {
    this.sessionBean = sessionBean;
    this.userService = userService;
  }

  public void login(@RequestBody LoginCommand command) {
	sessionBean.logout();
    String username = command.getUsername();
    String password = command.getPassword();
    User user =
        this.userService.findByUsername(username);

    if (validateUser(user, password)) sessionBean.login(user);
  }

  private boolean validateUser(User user, String password) {
	if(user == null || user.getPassword() == null) return false;
    return user.getPassword().equals(password);
  }
  
  public void logout()
  {
	  sessionBean.logout();
  }

  public boolean isLogged() {
    return sessionBean.isLogged();
  }
  
  public Type userRole() {
	  return sessionBean.getLoggedRole();
  }
}

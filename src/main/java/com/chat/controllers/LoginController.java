package com.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.dto.LoginCommand;
import com.chat.services.LoginService;

@RestController
public class LoginController {
  private LoginService loginService;

  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain")
  @ResponseBody
  public String login(@RequestBody LoginCommand command) {
    this.loginService.login(command);
    if(this.loginService.isLogged())
    	return String.valueOf(loginService.userRole());
    return "NOT_LOGGED";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/plain")
  @ResponseBody
  public String logged() {
    return String.valueOf(this.loginService.isLogged());
  }
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public void logout() {
    loginService.logout();
  }
}

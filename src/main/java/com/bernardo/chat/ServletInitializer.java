package com.bernardo.chat;

import com.bernardo.chat.domain.Type;
import com.bernardo.chat.domain.User;
import com.bernardo.chat.services.UserService;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	private UserService userService;

	public ServletInitializer(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		User user1 = new User("gustavo", "gustavo123@gmail.com", "qwerty", Type.ADMIN);
		this.userService.save(user1);

		return application.sources(ChatApplication.class);
	}

}

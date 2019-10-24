package com.mobileapp.ws.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.ws.models.request.UserDetailsRequestModel;
import com.mobileapp.ws.models.response.UserDetailsResponseModel;
import com.mobileapp.ws.service.UserService;
import com.mobileapp.ws.shared.dto.UserDto;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "Sefa arslan";
		
	}
	
	@PostMapping
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserDetailsResponseModel returnValue= new UserDetailsResponseModel();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser= userService.createdUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
		
	}

}

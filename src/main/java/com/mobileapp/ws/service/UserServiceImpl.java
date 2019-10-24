package com.mobileapp.ws.service;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobileapp.ws.UserRepository;
import com.mobileapp.ws.entity.UserEntity;
import com.mobileapp.ws.shared.Utils;
import com.mobileapp.ws.shared.dto.UserDto;

@Service
public  class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired	
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public UserDto createdUser(UserDto user) {
		
	//	if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("123456sefa");

		
		UserEntity userEntity= new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String publicUserId = utils.generateUserId(25);
		userEntity.setUserID(publicUserId);
		userEntity.setEncryptedPassword(encoder.encode(user.getPassword()));

		UserEntity storedUserDetails= userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 UserEntity userEntity= userRepository.findByEmail(email);
		 if (userEntity == null)
			throw new UsernameNotFoundException(email);
		 
		return new User(userEntity.getEmail(), 
				userEntity.getEncryptedPassword(), new ArrayList<>());
	}

}

package com.demand.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demand.site.common.entity.User;
import com.demand.site.repository.user.UserRepository;
import com.demand.site.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserByEmailAndPassword(String email, String password) throws Exception {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	
	
	
}

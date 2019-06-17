package com.examsystem.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examsystem.model.User;


@Service
public class LoginServiceImpl implements LoginService {
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	UserService userService;
	
	public boolean authenticateUser(String regNo,String pass) {
		
		User user = userService.loadUserByRegno(regNo);
		if(user!=null && user.getRegno().equals(regNo) && BCrypt.checkpw(pass, user.getPassword()))
			return true;
		else
			return false;
	}
}

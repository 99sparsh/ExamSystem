package com.examsystem.service;

import java.util.List;

import com.examsystem.model.SessionQuestionUtil;
import com.examsystem.model.User;
import com.examsystem.model.UserView;


public interface UserService {
	
	public List<SessionQuestionUtil> solveQuestions();
	
	public User loadUserByRegno(String reg);

	public void addUser(UserView userView) throws Exception;

	public void finalSubmit(List<SessionQuestionUtil> retrievedQuestions, int uid) throws Exception;

	public void calculateresult() throws Exception;
	
	
	
}

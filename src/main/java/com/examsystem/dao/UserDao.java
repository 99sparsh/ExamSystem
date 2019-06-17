package com.examsystem.dao;

import java.util.List;

import com.examsystem.model.Result;
import com.examsystem.model.Submission;
import com.examsystem.model.User;



public interface UserDao {
	
	
	public User findUserByRegno(String reg);
	
	public void addUser(User user) throws Exception;

	public void finalSubmit(Submission ob) throws Exception;
	
	public List<Submission> getSubmissions() throws Exception;

	public void addResult(Result ob) throws Exception;

}

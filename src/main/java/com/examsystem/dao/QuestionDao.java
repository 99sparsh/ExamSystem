package com.examsystem.dao;

import java.util.List;

import com.examsystem.model.Question;



public interface QuestionDao {
	
	public List<Question> getQuestions();
	
	public void addQuestion(Question question) throws Exception;
	
	public Question getQuestion(int id);
	
	public void deleteQuestion(int id) throws Exception;
	
	
	

}

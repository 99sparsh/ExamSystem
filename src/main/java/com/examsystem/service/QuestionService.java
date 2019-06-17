package com.examsystem.service;

import com.examsystem.model.Question;
import com.examsystem.model.QuestionResponse;
import com.examsystem.model.QuestionView;

public interface QuestionService {
	
    public QuestionResponse getQuestions();
	
	public void addQuestion(QuestionView questionView) throws Exception;
	
	public Question getQuestion(int id);
	
	public void deleteQuestion(int id) throws Exception;

}

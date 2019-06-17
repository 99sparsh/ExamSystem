package com.examsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examsystem.dao.QuestionDao;
import com.examsystem.model.Question;
import com.examsystem.model.QuestionResponse;
import com.examsystem.model.QuestionView;



@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDao questionDAO;
	

	@Override
	@Transactional
	public QuestionResponse getQuestions() {
		List<Question> questionList=questionDAO.getQuestions();
		List<String> errList=new ArrayList<String>();
		QuestionResponse queResponse=new QuestionResponse();
		if(questionList!=null && !questionList.isEmpty()) {
			queResponse.setQuestionList(questionList);
			queResponse.setValidResponse(true);
		}else {
			errList.add("No Question Available");
			queResponse.setValidResponse(false);
		}
		queResponse.setErrorList(errList);
		return queResponse;
	}

	@Override
	@Transactional
	public void addQuestion(QuestionView questionView) throws Exception {
		Question question = new Question();
		question.setBody(questionView.getBody());
		question.setAnswer(questionView.getAnswer());
		question.setOption1(questionView.getOption1());
		question.setOption2(questionView.getOption2());
		question.setOption3(questionView.getOption3());
		question.setPoints(questionView.getPoints());
		questionDAO.addQuestion(question);
		
	}

	@Override
	@Transactional
	public Question getQuestion(int id) {
		return questionDAO.getQuestion(id);
	}

	@Override
	@Transactional
	public void deleteQuestion(int id) throws Exception {
		questionDAO.deleteQuestion(id);
		
	}

	public QuestionDao getQuestionDAO() {
		return questionDAO;
	}

	public void setQuestionDAO(QuestionDao questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	
	

}

package com.examsystem.model;

import java.util.List;

public class QuestionResponse {
	private List<Question> questionList;
	
	private boolean isValidResponse;
	
	private List<String> errorList;

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public boolean isValidResponse() {
		return isValidResponse;
	}

	public void setValidResponse(boolean isValidResponse) {
		this.isValidResponse = isValidResponse;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
	

}

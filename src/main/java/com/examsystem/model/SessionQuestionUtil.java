package com.examsystem.model;

public class SessionQuestionUtil {
	
	private int originalId;
	private int newId;
	private String body;
	private String option1;
	private String option2;
	private String option3;
	private String submittedAnswer;
	
	@Override
	public String toString() {
		return "SessionQuestionUtil [originalId=" + originalId + ", newId=" + newId + ", body=" + body + ", option1="
				+ option1 + ", option2=" + option2 + ", option3=" + option3 + ", submittedAnswer=" + submittedAnswer
				+ "]";
	}
	public int getOriginalId() {
		return originalId;
	}
	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}
	public int getNewId() {
		return newId;
	}
	public void setNewId(int newId) {
		this.newId = newId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getSubmittedAnswer() {
		return submittedAnswer;
	}
	public void setSubmittedAnswer(String submittedAnswer) {
		this.submittedAnswer = submittedAnswer;
	}
	
	

}

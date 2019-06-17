package com.examsystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examsystem.dao.QuestionDao;
import com.examsystem.dao.UserDao;
import com.examsystem.model.Question;
import com.examsystem.model.Result;
import com.examsystem.model.SessionQuestionUtil;
import com.examsystem.model.Submission;
import com.examsystem.model.User;
import com.examsystem.model.UserView;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private QuestionDao questionDao;

	@Override
	@Transactional
	public List<SessionQuestionUtil> solveQuestions() {
		
		
		
		List<Question> q = questionDao.getQuestions();
		
		int qno[] = new int[10];
		
		List<SessionQuestionUtil>questions = new ArrayList<SessionQuestionUtil>();
		
		int length = q.size();
		
		for(int i=0; i<1; i++) {  //CHANGE LOOP COUNTER ACCORDING TO NUMBER OF QUESTIONS NEEDED PER USER PER SESSION
			
			qno[i] = (int)Math.random()*length;
			Question ques = q.get(qno[i]);
			SessionQuestionUtil ob = new SessionQuestionUtil();
			ob.setBody(ques.getBody());
			ob.setNewId(i+1);
			ob.setOption1(ques.getOption1());
			ob.setOption2(ques.getOption2());
			ob.setOption3(ques.getOption3());
			ob.setOriginalId(ques.getId());
			ob.setSubmittedAnswer("");
			
			questions.add(ob);
			
		}
		
		return questions;
		
	}
	
	
	@Override
	@Transactional 
	public void addUser(UserView userView) throws Exception {
		User user = new User();
		user.setAccess(userView.getAccess());
		user.setEmail(userView.getEmail());
		user.setFirstname(userView.getFirstname());
		user.setLastname(userView.getLastname());
		user.setRegno(userView.getRegno());
		user.setPassword(BCrypt.hashpw(userView.getPassword(), BCrypt.gensalt(10)));
		userDao.addUser(user);
		
	}
	
	@Override
	@Transactional
	public void finalSubmit(List<SessionQuestionUtil> retrievedQuestions, int uid) throws Exception {
		
		for(SessionQuestionUtil question : retrievedQuestions) {
			Submission ob = new Submission();
			ob.setAnswer(question.getSubmittedAnswer());
			ob.setQid(question.getOriginalId());
			ob.setUid(uid);
			userDao.finalSubmit(ob);
			
		}
		
	}


	@Override
	@Transactional 
	public User loadUserByRegno(String reg) {
		return userDao.findUserByRegno(reg);
		
	}
	
	@Override
	@Transactional
	public void calculateresult() throws Exception {
		
		List<Question>allQuestions = new ArrayList<Question>();
		List<Submission>allSubmissions = new ArrayList<Submission>();
		Map<Integer, Integer> userScores = new HashMap<Integer,Integer>(); // < UID , Score >
		Map<Integer,Integer>questionScores = new HashMap<Integer,Integer>(); // < QID , Points for that QID >
		Map<Integer,String>questionAnswers = new HashMap<Integer,String>(); // < QID , Ans of that QID >
		
		allQuestions = questionDao.getQuestions();
		allSubmissions = userDao.getSubmissions();
		
		
		for(int i=0; i<allQuestions.size(); i++) {
			questionAnswers.put(allQuestions.get(i).getId(),allQuestions.get(i).getAnswer());
		}
		for(int i=0; i<allQuestions.size(); i++) {
			questionScores.put(allQuestions.get(i).getId(),allQuestions.get(i).getPoints());
		}
		for(int i=0; i<allSubmissions.size(); i++) {
			userScores.put(allSubmissions.get(i).getUid(),0);
		}

		
		for(int i=0; i<allSubmissions.size(); i++) {
			int userKey = allSubmissions.get(i).getUid();
			int quesKey = allSubmissions.get(i).getQid();
			int value=0;
			if(allSubmissions.get(i).getAnswer().equals(questionAnswers.get(quesKey))) {
				value = userScores.get(userKey)+questionScores.get(quesKey);
				userScores.put(userKey,value);
			}
			
		}
		System.out.println(userScores.size());
		for(Map.Entry<Integer, Integer>entry: userScores.entrySet()) {
			//System.out.println(entry.getKey()+" : "+entry.getValue());
			Result ob = new Result();
			ob.setUid(entry.getKey());
			ob.setScore(entry.getValue());
			userDao.addResult(ob);
		}
		
	}
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}


	
	
}

package com.examsystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.examsystem.model.Question;

@Repository
public class QuestionDaoImpl implements QuestionDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Question> getQuestions(){
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Question> questions = currentSession.createQuery(" from Question").list();
		
		return questions;
		
	}

	@Override
	public void addQuestion(Question question) throws Exception {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(question);
		
	}

	@Override
	public Question getQuestion(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Question question = (Question) currentSession.createQuery(" from Question where where id=:QID").setParameter("QID", id).uniqueResult();
		
		return question;
	}

	@Override
	public void deleteQuestion(int id) throws Exception {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		 currentSession.createQuery("delete from Question where id=:QID")
		.setParameter("QID", id).executeUpdate();
		
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
}

package com.examsystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.examsystem.model.Result;
import com.examsystem.model.Submission;
import com.examsystem.model.User;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByRegno(String regno) {
		
		List<User> users = new ArrayList<User>();
		
		users = sessionFactory.getCurrentSession()
				.createQuery("from User where regno=?")
				.setParameter(0,regno)
				.list();
		
		if(users.size()>0)
			return users.get(0);
		else
			return null;
		
	}
	
	@Override
	public void addUser(User user) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
		
	}
	
	@Override
	public void finalSubmit(Submission submission) throws Exception {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(submission);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> getSubmissions() throws Exception {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<Submission> submissions = currentSession.createQuery(" from Submission").list();
		return submissions;

	}

	@Override
	public void addResult(Result ob) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(ob);
		
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}

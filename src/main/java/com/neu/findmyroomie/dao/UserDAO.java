package com.neu.findmyroomie.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;

import com.neu.findmyroomie.exception.PostException;
import com.neu.findmyroomie.exception.UserException;
import com.neu.findmyroomie.pojo.Admin;
import com.neu.findmyroomie.pojo.Post;
import com.neu.findmyroomie.pojo.Seeker;
import com.neu.findmyroomie.pojo.User;

public class UserDAO extends DAO {
	// Declaring a default constructor
	public UserDAO() {
	}

	// Registration for User
	public int registerUser(User user) throws UserException {
		int register = 0;
		try {
			begin();
			getSession().save(user);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	// Registration for Seeker
	public int registerSeeker(Seeker seeker) throws UserException {
		int register = 0;
		try {
			begin();
			getSession().save(seeker);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	// Authentication of various kind of users
	public String authenticateUser(String username, String password) throws UserException {
		String result = "notAUser";
		begin();
		if (username.startsWith("seeker_")) {
			Query query = getSession().getNamedQuery("authenticateSeeker");
			query.setParameter("username", username);
			Seeker seeker = (Seeker) query.uniqueResult();
			if (seeker == null || !BCrypt.checkpw(password, seeker.getPassword()))
				return result;
			else {
				result = username;
			}
		}
		else if (username.startsWith("user_")) {
			Query query = getSession().getNamedQuery("authenticateUser");
			query.setParameter("username", username);
			User user = (User) query.uniqueResult();
			if (user == null || !BCrypt.checkpw(password, user.getPassword()))
				return result;
			else {
				result = username;
			}
		}
		else {
			Query query = getSession().getNamedQuery("authenticateAdmin");
			query.setParameter("username", username);
			query.setParameter("password", password);
			Admin admin = (Admin) query.uniqueResult();
			if (admin == null)
				return result;
			else {
				result = username;
			}
		}
		commit();
		return result;
	}

	// Listing of users
	public List<User> userList() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User");
			List<User> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not list the User", e);
		}
	}

	// Removing Post
	public int removeUser(User user) throws PostException {
		int register = 0;
		try {
			begin();
			getSession().delete(user);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while creating post: " + e.getMessage());
		}
	}

	// Listing of Seekers
	public List<Seeker> seekerList() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Seeker");
			List<Seeker> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not list the Seeker", e);
		}
	}

	// Removing Seeker removeSeeker
	public int removeSeeker(Seeker seeker) throws PostException {
		int register = 0;
		try {
			begin();
			getSession().delete(seeker);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while creating post: " + e.getMessage());
		}
	}

	// Detail of a Seekers
	public List<Seeker> seekerDetails(String seekerName) throws UserException {
		try {
			begin();
			Criteria seekerDetail = getSession().createCriteria(Seeker.class);
			Criterion seekerFName = Restrictions.like("firstName", seekerName);
			Criterion seekerLName = Restrictions.like("lastName", seekerName);
			Criterion seekerUserName = Restrictions.like("userName", seekerName);
			Disjunction orExp = Restrictions.disjunction();
			orExp.add(seekerFName);
			orExp.add(seekerLName);
			orExp.add(seekerUserName);
			seekerDetail.add(orExp);
			List<Seeker> list = seekerDetail.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not list the seekerant", e);
		}
	}

	// Getting User details
	public User getUserDetail(String userName) throws UserException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(User.class);
			Criterion uName = Restrictions.eq("userName", userName);
			crit.add(uName);
			User cust = (User) crit.uniqueResult();
			commit();
			return cust;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not list the Customer", e);
		}
	}

}

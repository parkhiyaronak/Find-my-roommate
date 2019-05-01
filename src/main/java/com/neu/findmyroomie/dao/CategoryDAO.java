package com.neu.findmyroomie.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.neu.findmyroomie.exception.CategoryException;
import com.neu.findmyroomie.exception.CommentException;
import com.neu.findmyroomie.exception.PostException;
import com.neu.findmyroomie.exception.UserException;
import com.neu.findmyroomie.pojo.Category;
import com.neu.findmyroomie.pojo.Comments;
import com.neu.findmyroomie.pojo.Post;
import com.neu.findmyroomie.pojo.Seeker;

public class CategoryDAO extends DAO {

	public CategoryDAO() {
	}

	//Creating New Category
	public int createCategory(Category cat) throws PostException {
		int register = 0;
		try {
			begin();
			getSession().save(cat);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while creating category: " + e.getMessage());
		}
	}

	//Creating New Post
	public int createPost(Post post) throws PostException {
		int register = 0;
		try {
			begin();
			getSession().merge(post);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while creating post: " + e.getMessage());
		}
	}

	//Removing Post
	public int removePost(Post post) throws PostException {
		int register = 0;
		try {
			begin();
			post.getComments().clear();
			getSession().delete(post);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while creating post: " + e.getMessage());
		}
	}

	//Category List
	public List<Category> catList() throws CategoryException {
		try {
			begin();
			Query q = getSession().createQuery("from Category");
			List<Category> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Could not list the C", e);
		}
	}

	//Listing  all the posts from given Category
	public List<Post> postList(String catName) throws PostException {
		try {
			begin();
			Criteria postList = getSession().createCriteria(Post.class);
			Criteria catList = postList.createCriteria("category");
			catList.add(Restrictions.eq("categoryName", catName));
			List<Post> result = postList.list();
			commit();
			return result;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Could not list the post", e);
		}
	}

	//Post Details
	public List<Post> postDetails(String posttitle) throws PostException {
		try {
			begin();
			Criteria postDetail = getSession().createCriteria(Post.class);
			Criterion ptitle = Restrictions.like("title", posttitle);
			postDetail.add(ptitle);
			postDetail.setMaxResults(5);
			List<Post> list = postDetail.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Could not list the Post", e);
		}
	}

	//Updating Post
	public int updatePost(Post prod) throws PostException {
		int register = 0;
		try {
			begin();
			getSession().merge(prod);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while creating user: " + e.getMessage());
		}
	}

	//Getting description of one post
	public Post postDescription(String title) throws PostException {
		try {
			begin();
			Criteria postDetail = getSession().createCriteria(Post.class);
			Criterion postTitle = Restrictions.eq("title", title);
			postDetail.add(postTitle);
			Post p = (Post) postDetail.uniqueResult();
			commit();
			return p;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Could not list the Product", e);
		}

	}

	//Adding comment to a post
	public int addComment(Comments cmt) throws PostException {
		int register = 0;
		try {
			begin();
			getSession().merge(cmt);
			commit();
			register = 1;
			return register;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Exception while creating user: " + e.getMessage());
		}
	}

	//Getting comments on posts
	public List<Comments> getCommnent(String selectedPost) throws CommentException {
		try {
			begin();
			Criteria commentList = getSession().createCriteria(Comments.class);
			Criteria prod = commentList.createCriteria("post");
			prod.add(Restrictions.eq("title", selectedPost));
			List<Comments> result = commentList.list();
			commit();
			return result;
		} catch (HibernateException e) {
			rollback();
			throw new CommentException("Exception while creating user: " + e.getMessage());
		}
	}

	//Listing all Seekers
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

	//Listing all Posts
	public List<Post> allPostList() throws PostException {
		try {
			begin();
			Query q = getSession().createQuery("from Post");
			List<Post> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new PostException("Could not list the Seeker", e);
		}
	}
}

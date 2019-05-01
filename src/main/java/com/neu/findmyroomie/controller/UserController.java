package com.neu.findmyroomie.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.neu.findmyroomie.dao.CategoryDAO;
import com.neu.findmyroomie.dao.UserDAO;
import com.neu.findmyroomie.exception.CategoryException;
import com.neu.findmyroomie.exception.CommentException;
import com.neu.findmyroomie.exception.PostException;
import com.neu.findmyroomie.exception.UserException;
import com.neu.findmyroomie.pojo.Category;
import com.neu.findmyroomie.pojo.Comments;
import com.neu.findmyroomie.pojo.Post;
import com.neu.findmyroomie.pojo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/categoryList.htm", method = RequestMethod.GET)
	public ModelAndView getCategoryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		HttpSession session = req.getSession();
		ModelAndView mv = null;
		try {
			List<Category> catList = categoryDao.catList();
			session.setAttribute("catList", catList);
			mv = new ModelAndView("userViewPage");
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/getPost", method = RequestMethod.GET)
	public ModelAndView getproductList(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

		String selectedCategory = req.getParameter("selectedCategory");
		HttpSession session = req.getSession();
		ModelAndView mv = null;
		try {
			List<Post> prodList = categoryDao.postList(selectedCategory);
			session.setAttribute("allPostList", prodList);
			session.setAttribute("categoryName", selectedCategory);
			mv = new ModelAndView("postViewPage");
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/postData", method = RequestMethod.GET)
	public ModelAndView getproductDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, PostException {

		String selectedPost = req.getParameter("selectedPost");
		HttpSession session = req.getSession();
		ModelAndView mv = null;
		try {
			Post postList = categoryDao.postDescription(selectedPost);
			List<Comments> comment = categoryDao.getCommnent(selectedPost);
			session.setAttribute("postList", postList);
			session.setAttribute("commentList", comment);
			mv = new ModelAndView("postDescription");
		} catch (CommentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/getPDFView", method = RequestMethod.GET)
	public View getPDFViewPosttDetails(HttpServletRequest req, HttpServletResponse resp) {

		String selectedCategory = req.getParameter("selectedCategory");

		View view = new PostDetailPDFImpl(categoryDao, selectedCategory);
		return view;

	}

	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public ModelAndView addFeedback(HttpServletRequest req, HttpServletResponse resp) throws UserException {
		ModelAndView mv = null;
		Comments c = new Comments();
		HttpSession session = req.getSession();
		String comment = req.getParameter("comment");
		Post post = (Post) session.getAttribute("postList");
		String user = (String) session.getAttribute("userName");
		try {
			User cust = userDao.getUserDetail(user);
			c.setUser(cust);
			c.setComment(comment);
			c.setPost(post);
			int flag = categoryDao.addComment(c);
			if (flag == 1)
				mv = new ModelAndView("postDescription");
			else
				mv = new ModelAndView("errorRegister");
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}
	
	@RequestMapping(value = "/markAsInterested", method = RequestMethod.POST)
	public ModelAndView increaseInterestedCount(HttpServletRequest req, HttpServletResponse resp) throws UserException {
		ModelAndView mv = null;
		Comments c = new Comments();
		HttpSession session = req.getSession();
		Post post = (Post) session.getAttribute("postList");
		post.setInterestedCount(post.getInterestedCount()+1);
		try {
			int flag = categoryDao.updatePost(post);
			if (flag == 1)
				mv = new ModelAndView("postDescription");
			else
				mv = new ModelAndView("errorRegister");
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
}

package com.neu.findmyroomie.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.findmyroomie.dao.CategoryDAO;
import com.neu.findmyroomie.dao.UserDAO;
import com.neu.findmyroomie.exception.CategoryException;
import com.neu.findmyroomie.exception.PostException;
import com.neu.findmyroomie.exception.UserException;
import com.neu.findmyroomie.pojo.Category;
import com.neu.findmyroomie.pojo.Post;
import com.neu.findmyroomie.pojo.Seeker;
import com.neu.findmyroomie.pojo.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserDAO userDao;

	@Autowired
	private CategoryDAO categoryDao;

	// home page for admin
	@RequestMapping(value = "/adminCont.htm", method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request, ModelMap model, Post post) {
		ModelAndView mv = null;
		HttpSession session = request.getSession();
		// getting all the category list
		try {
			session.setAttribute("categoryList", categoryDao.catList());

		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			session.setAttribute("postList", categoryDao.allPostList());
		} catch (PostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			session.setAttribute("userList", userDao.userList());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			session.setAttribute("seekerList", userDao.seekerList());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv = new ModelAndView("adminViewPage");
		return mv;

	}

	@RequestMapping(value = "/createCategory.htm", method = RequestMethod.POST)
	public ModelAndView createCategory(HttpServletRequest req, ModelMap model, HttpSession session)
			throws ServletException {
		ModelAndView mv = null;
		;
		Category c = new Category();
		c.setCategoryName(req.getParameter("categoryName"));
		c.setCategoryDescript(req.getParameter("categoryDesc"));
		try {
			int flag = categoryDao.createCategory(c);
			if (flag == 1) {
				return new ModelAndView("adminViewPage");
			}
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/removePost.htm", method = RequestMethod.POST)
	public ModelAndView removePost(HttpServletRequest req, ModelMap model, HttpSession session)
			throws ServletException {
		ModelAndView mv = null;
		Post post = null;
		String postTitle = req.getParameter("postTitle");
		try {
			for (Post p : categoryDao.allPostList()) {
				if (p.getTitle().equals(postTitle)) {
					post = p;
					break;
				}
			}
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int flag = categoryDao.removePost(post);
			if (flag == 1) {
				return new ModelAndView("postRemoved");
			}
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/removeUser.htm", method = RequestMethod.POST)
	public ModelAndView removeUser(HttpServletRequest req, ModelMap model, HttpSession session)
			throws ServletException, PostException {
		ModelAndView mv = null;
		User user = null;
		String userId = req.getParameter("userId");
		try {
			for (User u : userDao.userList()) {
				if (u.getUserId() == (Long.parseLong(userId))) {
					user = u;
					break;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int flag = userDao.removeUser(user);
		if (flag == 1) {
			return new ModelAndView("spammerRemoved");
		}
		return null;
	}

	@RequestMapping(value = "/removeSeeker.htm", method = RequestMethod.POST)
	public ModelAndView removeSeeker(HttpServletRequest req, ModelMap model, HttpSession session)
			throws ServletException, PostException {
		ModelAndView mv = null;
		Seeker seeker = null;
		String seekerId = req.getParameter("seekerId");
		try {
			for (Seeker s : userDao.seekerList()) {
				if (s.getSeekerId() == (Long.parseLong(seekerId))) {
					seeker = s;
					break;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int flag = userDao.removeSeeker(seeker);
		if (flag == 1) {
			return new ModelAndView("spammerRemoved");
		}
		return null;
	}

}

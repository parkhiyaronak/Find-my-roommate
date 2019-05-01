package com.neu.findmyroomie.controller;

import java.util.List;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.neu.findmyroomie.dao.CategoryDAO;
import com.neu.findmyroomie.dao.UserDAO;
import com.neu.findmyroomie.exception.CategoryException;
import com.neu.findmyroomie.exception.PostException;
import com.neu.findmyroomie.exception.UserException;
import com.neu.findmyroomie.pojo.Address;
import com.neu.findmyroomie.pojo.Category;
import com.neu.findmyroomie.pojo.Post;
import com.neu.findmyroomie.pojo.Seeker;


@Controller
@RequestMapping("/seeker")
public class SeekerController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@RequestMapping(value = "/seekerCont.htm", method = RequestMethod.GET)
	public String showForm(HttpServletRequest request, ModelMap model, Post product) throws PostException, UserException, CategoryException {
		Seeker postSeeker = null;
		HttpSession session = request.getSession();
		//to get all the feedbacks of a particular product
		String seekerName = (String)session.getAttribute("seekerName");
		for(Seeker m:categoryDao.seekerList()) {
			if(m.getUserName().equals(seekerName)) {
				postSeeker = m;
				break;
			}
		}	
		session.setAttribute("categoryList", categoryDao.catList());
		return "seekerViewPage";
	}
	
	//to disallow spring to bind the category object
	@InitBinder
	public void customBinding(WebDataBinder binder) {
	    binder.setDisallowedFields(new String[] {"category"});
		
	}
	
	//to create new product
	@RequestMapping(value = "/seekerCont.htm", method = RequestMethod.POST)
	public ModelAndView addPost(@ModelAttribute("post") Post post,HttpServletRequest req,ModelMap model,HttpSession session) throws ServletException, PostException, UserException, CategoryException{
		ModelAndView mv=null;
		Category postCategory = null;
		Seeker postSeeker = null;
		
		String addressLine1 = req.getParameter("addressLine1");
		String addressLine2 = req.getParameter("addressLine2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zipCode = req.getParameter("zip");
		String country = req.getParameter("Country");
		
		Address address = new Address();
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipCode);
		address.setCountry(country);
	
		post.setPostAddress(address);
		
		//setting the photo of the product
		String localPath = "C:\\Users\\parkh\\Desktop\\WebtoolsFinalProjectImages";
		String photoNewName = generateFileName(post.getPhoto());
		post.setPostImage(photoNewName);
		try {
			post.getPhoto().transferTo(new File(localPath,photoNewName));		
		}catch(Exception e) {
		}	
		
		//Setting the category of the product
		String categoryName = req.getParameter("categoryName");
		for(Category c:categoryDao.catList()) {
			if(c.getCategoryName().equals(categoryName)) {
				postCategory = c;
				break;
			}
		}
		post.setCategory(postCategory);
		
		//setting Merchant of the new product
		String seekerName = (String)session.getAttribute("seekerName");
		for(Seeker m:categoryDao.seekerList()) {
			if(m.getUserName().equals(seekerName)) {
				postSeeker = m;
				post.setSeekerId(postSeeker.getSeekerId());
				break;
			}
		}
		try {
			int flag = categoryDao.createPost(post);
			if (flag==1) {
				return new ModelAndView("productaddedsuccessfully");
			}
			
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String generateFileName(MultipartFile multipart) {
		
		return new Date().getTime() + "-"+multipart.getOriginalFilename().replace(" ", "-");
	}
	
	//to display post form for updating
	@RequestMapping(value = "/seekerUpdate.htm", method = RequestMethod.GET)
	public ModelAndView updateProductForm(HttpServletRequest req, HttpServletResponse resp,HttpSession session,Post post) throws ServletException{
		ModelAndView mv=null;
		Post selectedPost = null;
		String title = req.getParameter("title");
		
		try {
			List<Post> posts = new ArrayList<Post>();
			posts =  categoryDao.postDetails(title);
			for(Post p:posts) {
				if(p.getTitle().equals(title)) {
					selectedPost = p;
					break;
				}
			}
			session = req.getSession();
			session.setAttribute("post", selectedPost);
			return new ModelAndView("updatePost");
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	//to update the product as given by merchant
	@RequestMapping(value = "/seekerUpdate.htm", method = RequestMethod.POST)
	public ModelAndView updateProduct(Post post,HttpServletRequest req,ModelMap model, HttpSession session) throws ServletException{
		ModelAndView mv=null;
		post = (Post) session.getAttribute("post");
		post.setDescription(req.getParameter("descprition"));
		post.setAvailable(Boolean.parseBoolean(req.getParameter("available")));
		try {
			int flag = categoryDao.updatePost(post);
			if (flag==1) {
				return new ModelAndView("unavailablePost");
			}
			
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

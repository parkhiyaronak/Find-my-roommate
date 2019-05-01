package com.neu.findmyroomie.controller;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.neu.findmyroomie.dao.UserDAO;
import com.neu.findmyroomie.exception.UserException;
import com.neu.findmyroomie.pojo.Address;
import com.neu.findmyroomie.pojo.Seeker;
import com.neu.findmyroomie.pojo.User;

@Controller
@RequestMapping("/")
public class LoginRegisterActivityController {
	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "userLogin";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "userLogin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		ModelAndView mv = null;
		String user = req.getParameter("username");
		String pswd = req.getParameter("password");
		try {
			String checkValidUser = userDao.authenticateUser(user, pswd);
			if (checkValidUser.startsWith("admin")) {
				HttpSession session = req.getSession();
				session.setAttribute("adminName", user);
				mv = new ModelAndView(new RedirectView("admin/adminCont.htm", false));
			} else if (checkValidUser.startsWith("seeker_")) {
				HttpSession session = req.getSession();
				session.setAttribute("seekerName", user);
				mv = new ModelAndView(new RedirectView("seeker/seekerCont.htm", false));
			} else if (checkValidUser.startsWith("user_")) {
				HttpSession session = req.getSession();
				session.setAttribute("userName", user);
				mv = new ModelAndView(new RedirectView("user/categoryList.htm", false));
			} else if (checkValidUser.equalsIgnoreCase("notAUser"))
				mv = new ModelAndView("registerUser");

		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest req, HttpServletResponse resp) throws UserException {
		ModelAndView mv = null;

		String pswd = req.getParameter("password");
		String confirmpswd = req.getParameter("confirmpassword");

		if (pswd.equals(confirmpswd)) {
			String fName = req.getParameter("firstName");
			String lName = req.getParameter("lastName");
			String emailId = req.getParameter("emailId");
			String username = req.getParameter("username");
			String phone = req.getParameter("phone");
			String hashedPswd = BCrypt.hashpw(pswd, BCrypt.gensalt());
			String userType = req.getParameter("usertype");

			if (userType.equalsIgnoreCase("Seeker")) {
				Seeker seeker = new Seeker();
				seeker.setFirstName(fName);
				seeker.setLastName(lName);
				seeker.setEmailId(emailId);
				seeker.setUserName("seeker_" + username);
				seeker.setPassword(hashedPswd);

				int flag = userDao.registerSeeker(seeker);
				try {
					SendEmail("ronakparakhiya@gmail.com", seeker.getUserName());
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (flag == 1)
					mv = new ModelAndView("home");
			} else if (userType.equalsIgnoreCase("User")) {
				User user = new User();
				user.setFirstName(fName);
				user.setLastName(lName);
				user.setEmailId(emailId);
				user.setUserName("user_" + username);
				user.setPhoneNumber("phone");
				user.setPassword(hashedPswd);

				int flag = userDao.registerUser(user);
				try {
					SendEmail("ronakparakhiya@gmail.com", user.getUserName());
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (flag == 1)
					mv = new ModelAndView("home");
			}
		} else {
			mv = new ModelAndView("errorRegister");
		}
		return mv;
	}

	public void SendEmail(String emailID, String username) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		// User your gmail username and password
		email.setAuthenticator(new DefaultAuthenticator("ronaktestdev@gmail.com", "Test@123"));
		email.setSSLOnConnect(true);
		email.setFrom("no-reply@msis.neu.edu");
		email.setSubject("TestMail");
		email.setMsg("Registered successfully	" + "/n Your username is	" + username);
		email.addTo(emailID);
		email.send();
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/registerNewUser", method = RequestMethod.GET)
	public ModelAndView newUserRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		return new ModelAndView("registerUser");

	}
}

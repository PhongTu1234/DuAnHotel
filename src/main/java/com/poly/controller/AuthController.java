package com.poly.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.DAO.UserDAO;
import com.poly.Service.UserService;
import com.poly.entity.Users;

import net.bytebuddy.utility.RandomString;

@Controller
public class AuthController {

	@Autowired
	UserDAO accountDAO;

	@Autowired
	UserService accountService;


	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/auth/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}

	@RequestMapping("/auth/login/form")
	public String logInForm(Model model, @ModelAttribute("users") Users users) {
		return "auth/login_register";
	}

	@RequestMapping("/auth/login/success")
	public String logInSuccess(Model model, @ModelAttribute("users") Users users) {
		model.addAttribute("message", "Logged in successfully");
		return "redirect:/index";
	}

	@RequestMapping("/auth/login/error")
	public String logInError(Model model, @Validated @ModelAttribute("users") Users users, Errors errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Wrong login information!");
			return "auth/login_register";
		}
		return "auth/login_register";
	}

	@RequestMapping("/auth/unauthoried")
	public String unauthoried(Model model, @ModelAttribute("users") Users users) {
		model.addAttribute("message", "You don't have access!");
		return "auth/login_register";
	}

	@RequestMapping("/auth/logout/success")
	public String logOutSuccess(Model model, @ModelAttribute("users") Users users) {
		model.addAttribute("message", "You are logged out!");
		return "auth/login_register";
	}

	// OAuth2
	@RequestMapping("/oauth2/login/success")
	public String oauth2(OAuth2AuthenticationToken oauth2) {
		accountService.loginFromOAuth2(oauth2);
		return "forward:/auth/login_register/success";
	}

	@GetMapping("/auth/register")
	public String signUpForm(Model model) {
		model.addAttribute("account", new Users());
		return "auth/register";
	}

	@PostMapping("/auth/register")
	public String signUpSuccess(Model model, @Validated @ModelAttribute("users") Users users, Errors error,
			HttpServletResponse response) {
		if (error.hasErrors()) {
			model.addAttribute("message", "Please correct the error below!");
			return "auth/register";
		}
//		account.setPhoto("user.png");
//		account.setToken("token");
		accountService.create(users);
		model.addAttribute("message", "New account registration successful!");
		response.addHeader("refresh", "2;url=/auth/login/form");
		return "auth/register";
	}

	@GetMapping("/auth/forgot-password")
	public String forgotPasswordForm(Model model) {
		return "auth/forgot-password";
	}

	@PostMapping("/auth/forgot-password")
	public String processForgotPassword(@RequestParam("email") String email, HttpServletRequest request, Model model)
			throws Exception {
		try {
			String token = RandomString.make(50);
			accountService.updateToken(token, email);
			String resetLink = getSiteURL(request) + "/auth/reset-password?token=" + token;
			//mailer.sendEmail(email, resetLink);
			model.addAttribute("message", "We have sent a reset password link to your email. "
					+ "If you don't see the email, check your spam folder.");
		} catch (MessagingException e) {
			e.printStackTrace();
			model.addAttribute("error", "Error while sending email");
		}
		return "auth/forgot-password";
	}

	public String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
}

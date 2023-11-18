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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.poly.DAO.UserDAO;
import com.poly.Service.AuthorityService;
import com.poly.Service.UserService;
import com.poly.entity.Users;

import net.bytebuddy.utility.RandomString;

@RequestMapping("/auth")
@Controller
public class AuthController {

	@Autowired
	UserDAO accountDAO;

	@Autowired
	UserService accountService;
	
	@Autowired
	AuthorityService authService;

//	@Autowired
//	MailerService mailer;
	//xu li web
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/auth/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}

	@RequestMapping("/auth/login/form")
	public String logInForm(Model model, @ModelAttribute("Users") Users users) {
		return "auth/login_register";
	}

	@RequestMapping("/auth/login/success")
	public String logInSuccess(Model model, @ModelAttribute("Users") Users users) {
		model.addAttribute("message", "Logged in successfully");
		return "redirect:/index";
	}

	@RequestMapping("/auth/login/error")
	public String logInError(Model model, @Validated @ModelAttribute("Users") Users users, Errors errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Wrong login information!");
			return "auth/login_register";
		}
		return "auth/login_register";
	}

	@RequestMapping("/auth/unauthoried")
	public String unauthoried(Model model, @ModelAttribute("Users") Users users) {
		model.addAttribute("message", "You don't have access!");
		return "auth/login_register";
	}

	@RequestMapping("/auth/logout/success")
	public String logOutSuccess(Model model, @ModelAttribute("Users") Users users) {
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
		model.addAttribute("Users", new Users());
		return "auth/login_register";
	}

	@PostMapping("/auth/register")
	public String signUpSuccess(Model model, @Validated @ModelAttribute("Users") Users account, Errors error,
			HttpServletResponse response) {
		if (error.hasErrors()) {
			model.addAttribute("message", "Please correct the error below!");
			return "auth/login_register";
		}
		//account.setPhoto("user.png");
		account.setToken("token");
		accountService.create(account);
		model.addAttribute("message", "New account registration successful!");
		response.addHeader("refresh", "2;url=/auth/login_register/form");
		return "auth/login_register";
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

	@GetMapping("/auth/reset-password")
	public String resetPasswordForm(@Param(value = "token") String token, Model model) {
		Users account = accountService.getByToken(token);
		model.addAttribute("token", token);
		if (account == null) {
			model.addAttribute("message", "Invalid token!");
			return "redirect:/auth/login_register/form";
		}
		return "auth/reset-password";
	}

	@PostMapping("/auth/reset-password")
	public String processResetPassword(@RequestParam("token") String code, @RequestParam("password") String password,
			HttpServletResponse response, Model model) {
		Users token = accountService.getByToken(code);
		if (token == null) {
			model.addAttribute("message", "Invalid token!");
		} else {
			accountService.updatePassword(token, password);
			model.addAttribute("message", "You have successfully changed your password!");
			response.addHeader("refresh", "2;url=/auth/login_register/form");
		}
		return "auth/reset-password";
	}

	@GetMapping("/auth/change-password")
	public String changePasswordForm(Model model) {
		return "auth/change-password";
	}

	@PostMapping("/auth/change-password")
	public String processChangePassword(Model model, @RequestParam("username") String username,
			@RequestParam("password") String newPassword) {
		Users account = accountService.findById(username);
		accountService.changePassword(account, newPassword);
		model.addAttribute("message", "Change password successfully!");
		return "auth/change-password";
	}

	public String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
	
	
	//xu ly admin

	@GetMapping("/form")
 	public String formUser(Model model) {
 		model.addAttribute("user", new Users());
 		return "admin/authority/form";
 	}

 	@GetMapping("/index")
    public String showUsersIndex(Model model) {
 		model.addAttribute("auth", authService.findAll());
        return "admin/authority/index";
    }
 
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("auth", authService.findAll());
        return "Roles/index";
    }

    @GetMapping("/{cmt}")
    public String viewUser(@PathVariable("cmt") String cmt, Model model) {
        Users user = accountService.findById(cmt);
        model.addAttribute("auth", user);
        return "admin/authority/form";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute Users user) {
    	accountService.create(user);
        return "redirect:/users/form";
    }

//    @PostMapping("/update")
//    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//        userService.update(user);
//        return new ModelAndView("redirect:/users/index");
//    }
//
//    @GetMapping("/delete/{cmt}")
//    public ModelAndView deleteUser(@PathVariable String cmt) {
//        userService.delete(cmt);
//        return new ModelAndView("redirect:/users/index");
//    }
    
    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute Users user) {
        if (user.getCmt() != null) {
            // Nếu có ID, thực hiện cập nhật
        	accountService.update(user);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	accountService.create(user);
        }
        return new ModelAndView("redirect:/users/index");
    }

    @GetMapping("/delete/{cmt}")
    public ModelAndView deleteUser(@PathVariable("cmt") String cmt) {
    	accountService.delete(cmt);
        return new ModelAndView("redirect:/users/index");
    }
}

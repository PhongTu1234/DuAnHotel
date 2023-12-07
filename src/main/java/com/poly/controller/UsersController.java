package com.poly.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.RoleService;
import com.poly.Service.UserService;
import com.poly.entity.Authority;
import com.poly.entity.Hotels;
import com.poly.entity.Role;
import com.poly.entity.RoomTypes;
import com.poly.entity.Services;
import com.poly.entity.Users;

@Controller
public class UsersController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@GetMapping("users/form")
    public String userForm(Model model) {
        model.addAttribute("users", new Users());
        model.addAttribute("roles", roleService.findShop());
        return "admin/Users/form";
    }

    @GetMapping("users/{cmt}")
    public String viewUser(@PathVariable("cmt") String cmt, Model model) {
        Users user = userService.findById(cmt);
        model.addAttribute("users", user);
        model.addAttribute("roles", roleService.findShop());
        return "admin/Users/form";
    }

//    private boolean hasRole(String roleId, List<Authority> authorities) {
//        for (Authority authority : authorities) {
//            if (authority.getRole().getId().equals(roleId)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private boolean hasRole(String cmt, String roleId) {
//        Users user = userService.findById(cmt);
//        if (user != null && user.getAuthorities() != null) {
//            return hasRole(roleId, user.getAuthorities());
//        }
//        return false;
//    }

	@GetMapping("/users/index")
	public String showUsersIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 3);
		Page<Users> user = userService.findAll(page);
		model.addAttribute("users", user);

		return "admin/Users/index";
	}
	
//	    @GetMapping
//	    public String listUsers(Model model) {
//	        model.addAttribute("users", userService.findAll());
//	        return "Roles/index";
//	    }

	

	@PostMapping("/users/create")
	public String createUser(@ModelAttribute Users user) {
		userService.create(user);
		return "redirect:/users/form";
	}

//	    @PostMapping("/update")
//	    public ModelAndView updateUser(@Validated @ModelAttribute("user") Users user) {
//	        userService.update(user);
//	        return new ModelAndView("redirect:/users/index");
//	    }
//
//	    @GetMapping("/delete/{cmt}")
//	    public ModelAndView deleteUser(@PathVariable String cmt) {
//	        userService.delete(cmt);
//	        return new ModelAndView("redirect:/users/index");
//	    }

	@PostMapping("/users/update")
	public ModelAndView updateUser(@ModelAttribute Users user) {
		if (user.getCmt() != null) {
			// Nếu có ID, thực hiện cập nhật
			userService.update(user);
		} else {
			// Nếu không có ID, thực hiện thêm mới
			userService.create(user);
		}
		return new ModelAndView("redirect:/users/index");
	}

	@GetMapping("/users/delete/{cmt}")
	public ModelAndView deleteUser(@PathVariable("cmt") String cmt) {
		userService.delete(cmt);
		return new ModelAndView("redirect:/users/index");
	}

}
package com.poly.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.AuthorityService;
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
	
	@Autowired
	AuthorityService authorityService;

	@GetMapping("users/form")
    public String userForm(Model model) {
        model.addAttribute("users", new Users());
        model.addAttribute("roles", roleService.findShop());
        return "admin/Users/form";
    }

	@GetMapping("/users/index")
	public String showUsersIndex(Model model, @RequestParam(name = "p", defaultValue = "1") Integer p) {
		Pageable page = PageRequest.of(p-1, 10);
		Page<Users> user = userService.findAll(page);
		model.addAttribute("users", user);

		return "admin/Users/index";
	}

    @GetMapping("users/{cmt}")
    public String viewUser(@PathVariable("cmt") String cmt, Model model) {
        Users user = userService.findById(cmt);
        model.addAttribute("users", user);
        model.addAttribute("roles", roleService.findShop());
        return "admin/Users/form";
    }
	
    @PostMapping("users/update")
    public ModelAndView updateUserInfo(@Valid @ModelAttribute Users users,
                                        @RequestParam("selectedRoles") List<String> selectedRoleIds,
                                        BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/Users/form");
            return modelAndView;
        }

        authorityService.deleteAuthoritiesByUserCmt(users.getCmt());
        
        userService.updateRoles(users.getCmt(), selectedRoleIds);

        if (users.getCmt() != null) {
        	
            userService.update(users);
        } else {
            userService.create(users);
        }
        
        modelAndView.setViewName("redirect:/users/index");
        return modelAndView;
    }
    
	@PostMapping("/users/create")
	public String createUser(@ModelAttribute Users user) {
		userService.create(user);
		return "redirect:/users/form";
	}

	@GetMapping("/users/delete/{cmt}")
	public ModelAndView deleteUser(@PathVariable("cmt") String cmt) {
		userService.delete(cmt);
		return new ModelAndView("redirect:/users/index");
	}

}
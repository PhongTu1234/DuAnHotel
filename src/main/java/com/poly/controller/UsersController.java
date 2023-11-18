package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.poly.Service.UserService;
import com.poly.entity.Users;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String showUsersIndex() {
        return "users/index";
    }
    
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/{cmt}")
    public String viewUser(@PathVariable String cmt, Model model) {
        Users user = userService.findById(cmt);
        model.addAttribute("user", user);
        return "users/view";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new Users());
        return "users/create";
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute Users user) {
        userService.create(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/update/{cmt}")
    public String updateUserForm(@PathVariable String cmt, Model model) {
        Users user = userService.findById(cmt);
        model.addAttribute("user", user);
        return "users/update";
    }

    @PostMapping("/update/{cmt}")
    public ModelAndView updateUser(@PathVariable String cmt, @ModelAttribute Users user) {
        userService.update(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/delete/{cmt}")
    public ModelAndView deleteUser(@PathVariable String cmt) {
        userService.delete(cmt);
        return new ModelAndView("redirect:/users");
    }
}
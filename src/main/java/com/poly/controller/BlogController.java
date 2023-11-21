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

import com.poly.Service.BlogsService;
import com.poly.Service.ImagesService;
import com.poly.entity.Blogs;
import com.poly.entity.Images;

@Controller
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
    private BlogsService blogsService;
	
	
	@RequestMapping("blog")
	public String blog() {
		return "blog-listview";
	}

	@RequestMapping("/blog/detail")
	public String detail() {
		return "blog-detail";
	}

	
	//xu ly admin
 	@GetMapping("/form")
 	public String formBlog(Model model) {
 		model.addAttribute("blogs", new Blogs());
 		return "admin/Blog/form";
 	}

 	@GetMapping("/index")
    public String showBlogsIndex(Model model) {
 		model.addAttribute("blogs", blogsService.findAll());
        return "admin/Blog/index";
    }
 
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable("id") Integer id, Model model) {
    	Blogs blogs = blogsService.findById(id);
        model.addAttribute("blogs", blogs);
        return "admin/Blog/form";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute Blogs blogs) {
    	blogsService.create(blogs);
        return "redirect:/blogs/form";
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
    public ModelAndView updateBlog(@ModelAttribute Blogs blogs) {
        if (blogs.getId() != null) {
            // Nếu có ID, thực hiện cập nhật
        	blogsService.update(blogs);
        } else {
            // Nếu không có ID, thực hiện thêm mới
        	blogsService.create(blogs);
        }
        return new ModelAndView("redirect:/blogs/index");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteBlog(@PathVariable("id") Integer id) {
    	blogsService.delete(id);
        return new ModelAndView("redirect:/blogs/index");
    }
}

package com.poly.controller;

import java.util.List;

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

import com.poly.Service.BlogsService;
import com.poly.Service.ImagesService;
import com.poly.entity.Blogs;
import com.poly.entity.Booking_Room;
import com.poly.entity.Hotels;
import com.poly.entity.Images;

@Controller
public class BlogController {

	@Autowired
    private BlogsService blogsService;
	
	@Autowired
    private ImagesService imagesService;
	
	
	@RequestMapping("blog")
	public String blog() {
		return "blog-listview";
	}

	@RequestMapping("/blog/detail")
	public String detail() {
		return "blog-detail";
	}

	
	//xu ly admin
 	@GetMapping("/blogs/form")
 	public String formBlog(Model model) {
 		model.addAttribute("blogs", new Blogs());
 		return "admin/Blog/form";
 	}

 	@GetMapping("/blogs/index")
    public String showBlogsIndex(Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
 		Pageable page = PageRequest.of(p, 10);
		Page<Blogs> blogs = blogsService.findAll(page);
		model.addAttribute("blogs", blogs);
		return "admin/Blog/index";
	}
 	
//    @GetMapping
//    public String listPlaces(Model model) {
//        model.addAttribute("places", placeService.findAll());
//        return "";
//    }

    @GetMapping("/blogs/{id}")
    public String viewBlog(@PathVariable("id") Integer id, Model model) {
    	Blogs blogs = blogsService.findById(id);
        model.addAttribute("blogs", blogs);
        return "admin/Blog/form";
    }

    @PostMapping("/blogs/create")
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
    
    @PostMapping("/blogs/update")
    public ModelAndView updateBlog(@ModelAttribute Blogs blogs, Model model) {
//        if (blogs.getId() != null) {
//            // Nếu có ID, thực hiện cập nhật
//        	blogsService.update(blogs);
//        } else {
//            // Nếu không có ID, thực hiện thêm mới
//        	blogsService.create(blogs);
//        }
//        return new ModelAndView("redirect:/blogs/index");
    	
//    	String Image = blogs.getImages().getName();
//        if(imagesService.findByImageName(Image) != null ) {
        	if (blogs.getId() != null) {
	            // Nếu có ID, thực hiện cập nhật
        		//blogs.setImages(imagesService.findByImageName(Image));
	        	blogsService.update(blogs);
	        } else {
	        	//blogs.setImages(imagesService.findByImageName(Image));
	            // Nếu không có ID, thực hiện thêm mới
	        	blogsService.create(blogs);
	        }
        	return new ModelAndView("redirect:/blogs/index");
//        }else {
//        	model.addAttribute("message", "Hình ảnh không tồn tại");
//        	return new ModelAndView("admin/Blog/form");
//        }
    }

    @GetMapping("/blogs/delete/{id}")
    public ModelAndView deleteBlog(@PathVariable("id") Integer id) {
    	blogsService.delete(id);
        return new ModelAndView("redirect:/blogs/index");
    }
}

package com.poly.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Blogs;
import com.poly.Service.BlogsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blogs")
public class BlogsRestController {

	@Autowired
	BlogsService HotelTypesService;

	@GetMapping
	public List<Blogs> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Blogs getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Blogs create(@RequestBody Blogs Blogs) {
		return HotelTypesService.create(Blogs);
	}

	@PutMapping("{id}")
	public Blogs update(@PathVariable("id") String id, @RequestBody Blogs Blogs) {
		return HotelTypesService.update(Blogs);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

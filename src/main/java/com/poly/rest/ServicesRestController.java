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

import com.poly.Service.ServiceService;
import com.poly.entity.Services;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Services")
public class ServicesRestController {

	@Autowired
	ServiceService HotelTypesService;

	@GetMapping
	public List<Services> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Services getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Services create(@RequestBody Services Services) {
		return HotelTypesService.create(Services);
	}

	@PutMapping("{id}")
	public Services update(@PathVariable("id") String id, @RequestBody Services Services) {
		return HotelTypesService.update(Services);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

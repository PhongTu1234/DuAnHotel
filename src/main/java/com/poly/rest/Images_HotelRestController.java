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

import com.poly.entity.Images_Hotel;
import com.poly.Service.Images_HotelService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Images_Hotel")
public class Images_HotelRestController {

	@Autowired
	Images_HotelService HotelTypesService;

	@GetMapping
	public List<Images_Hotel> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Images_Hotel getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Images_Hotel create(@RequestBody Images_Hotel Images_Hotel) {
		return HotelTypesService.create(Images_Hotel);
	}

	@PutMapping("{id}")
	public Images_Hotel update(@PathVariable("id") String id, @RequestBody Images_Hotel Images_Hotel) {
		return HotelTypesService.update(Images_Hotel);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

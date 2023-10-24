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

import com.poly.entity.Places;
import com.poly.Service.PlacesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Places")
public class PlacesRestController {

	@Autowired
	PlacesService HotelTypesService;

	@GetMapping
	public List<Places> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Places getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Places create(@RequestBody Places Places) {
		return HotelTypesService.create(Places);
	}

	@PutMapping("{id}")
	public Places update(@PathVariable("id") String id, @RequestBody Places Places) {
		return HotelTypesService.update(Places);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}
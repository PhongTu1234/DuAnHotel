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

import com.poly.entity.HotelTypes;
import com.poly.Service.HotelTypesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/hoteltypes")
public class HotelTypesRestController {

	@Autowired
	HotelTypesService HotelTypesService;

	@GetMapping
	public List<HotelTypes> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public HotelTypes getOne(@PathVariable("id") String id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public HotelTypes create(@RequestBody HotelTypes HotelTypes) {
		return HotelTypesService.create(HotelTypes);
	}

	@PutMapping("{id}")
	public HotelTypes update(@PathVariable("id") String id, @RequestBody HotelTypes HotelTypes) {
		return HotelTypesService.update(HotelTypes);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		HotelTypesService.delete(id);
	}
}

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

import com.poly.entity.Images;
import com.poly.Service.ImagesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Images")
public class ImagesRestController {

	@Autowired
	ImagesService HotelTypesService;

	@GetMapping
	public List<Images> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Images getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Images create(@RequestBody Images Images) {
		return HotelTypesService.create(Images);
	}

	@PutMapping("{id}")
	public Images update(@PathVariable("id") String id, @RequestBody Images Images) {
		return HotelTypesService.update(Images);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

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

import com.poly.entity.Feedback;
import com.poly.Service.FeedbackService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Feedback")
public class FeedbackRestController {

	@Autowired
	FeedbackService HotelTypesService;

	@GetMapping
	public List<Feedback> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Feedback getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Feedback create(@RequestBody Feedback Feedback) {
		return HotelTypesService.create(Feedback);
	}

	@PutMapping("{id}")
	public Feedback update(@PathVariable("id") String id, @RequestBody Feedback Feedback) {
		return HotelTypesService.update(Feedback);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

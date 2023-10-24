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

import com.poly.entity.Bookings;
import com.poly.Service.BookingsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Bookings")
public class BookingsRestController {

	@Autowired
	BookingsService HotelTypesService;

	@GetMapping
	public List<Bookings> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Bookings getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Bookings create(@RequestBody Bookings Bookings) {
		return HotelTypesService.create(Bookings);
	}

	@PutMapping("{id}")
	public Bookings update(@PathVariable("id") String id, @RequestBody Bookings Bookings) {
		return HotelTypesService.update(Bookings);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

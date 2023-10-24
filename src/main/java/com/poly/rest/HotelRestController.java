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

import com.poly.entity.Hotels;
import com.poly.Service.HotelService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/hotels")
public class HotelRestController {
	@Autowired
	HotelService hotelService;

	@GetMapping
	public List<Hotels> getAll() {
		return hotelService.findAll();
	}

	@GetMapping("{id}")
	public Hotels getOne(@PathVariable("id") Integer htid) {
		return hotelService.findById(htid);
	}

	@PostMapping
	public Hotels create(@RequestBody Hotels hotel) {
		return hotelService.create(hotel);
	}

	@PutMapping("{id}")
	public Hotels update(@PathVariable("id") Integer htid, @RequestBody Hotels hotel) {
		return hotelService.update(hotel);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer htid) {
		hotelService.delete(htid);
	}
}

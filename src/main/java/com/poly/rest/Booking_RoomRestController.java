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

import com.poly.entity.Booking_Room;
import com.poly.Service.Booking_RoomService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Booking_Room")
public class Booking_RoomRestController {

	@Autowired
	Booking_RoomService HotelTypesService;

	@GetMapping
	public List<Booking_Room> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Booking_Room getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Booking_Room create(@RequestBody Booking_Room Booking_Room) {
		return HotelTypesService.create(Booking_Room);
	}

	@PutMapping("{id}")
	public Booking_Room update(@PathVariable("id") String id, @RequestBody Booking_Room Booking_Room) {
		return HotelTypesService.update(Booking_Room);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

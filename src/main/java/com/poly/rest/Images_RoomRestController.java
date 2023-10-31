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

import com.poly.Service.Images_RoomService;
import com.poly.entity.Images_Room;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Images_Room")
public class Images_RoomRestController {

	@Autowired
	Images_RoomService HotelTypesService;

	@GetMapping
	public List<Images_Room> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Images_Room getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Images_Room create(@RequestBody Images_Room Images_Room) {
		return HotelTypesService.create(Images_Room);
	}

	@PutMapping("{id}")
	public Images_Room update(@PathVariable("id") String id, @RequestBody Images_Room Images_Room) {
		return HotelTypesService.update(Images_Room);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

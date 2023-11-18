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

import com.poly.Service.Service_RoomService;
import com.poly.entity.Service_Rooms;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Service_Room")
public class Service_RoomRestController {

	@Autowired
	Service_RoomService HotelTypesService;

	@GetMapping
	public List<Service_Rooms> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Service_Rooms getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Service_Rooms create(@RequestBody Service_Rooms Service_Room) {
		return HotelTypesService.create(Service_Room);
	}

	@PutMapping("{id}")
	public Service_Rooms update(@PathVariable("id") String id, @RequestBody Service_Rooms Service_Room) {
		return HotelTypesService.update(Service_Room);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

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

import com.poly.Service.ServiceRoomsService;
import com.poly.entity.ServiceRooms;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Service_Room")
public class ServiceRoomsRestController {

	@Autowired
	ServiceRoomsService HotelTypesService;


	@GetMapping("{id}")
	public ServiceRooms getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public ServiceRooms create(@RequestBody ServiceRooms Service_Room) {
		return HotelTypesService.create(Service_Room);
	}

	@PutMapping("{id}")
	public ServiceRooms update(@PathVariable("id") String id, @RequestBody ServiceRooms Service_Room) {
		return HotelTypesService.update(Service_Room);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

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

import com.poly.Service.RoomTypesService;
import com.poly.entity.RoomTypes;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roomtypes")
public class RoomTypesRestController {

	@Autowired
	RoomTypesService RoomTypesService;

	@GetMapping
	public List<RoomTypes> getAll() {
		return RoomTypesService.findAll();
	}

	@GetMapping("{id}")
	public RoomTypes getOne(@PathVariable("id") Integer id) {
		return RoomTypesService.findById(id);
	}

	@PostMapping
	public RoomTypes create(@RequestBody RoomTypes RoomTypes) {
		return RoomTypesService.create(RoomTypes);
	}

	@PutMapping("{id}")
	public RoomTypes update(@PathVariable("id") String id, @RequestBody RoomTypes RoomTypes) {
		return RoomTypesService.update(RoomTypes);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		RoomTypesService.delete(id);
	}
}

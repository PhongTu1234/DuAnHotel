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

import com.poly.Service.RoomService;
import com.poly.entity.Rooms;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/rooms")
public class RoomRestController {
	@Autowired
	RoomService RoomService;

	@GetMapping
	public List<Rooms> getAll() {
		return RoomService.findAll();
	}

	@GetMapping("{rtid}")
	public Rooms getOne(@PathVariable("rtid") Integer htid) {
		return RoomService.findById(htid);
	}

	@PostMapping
	public Rooms create(@RequestBody Rooms Rooms) {
		return RoomService.create(Rooms);
	}

	@PutMapping("{rtid}")
	public Rooms update(@PathVariable("rtid") Integer htid, @RequestBody Rooms Rooms) {
		return RoomService.update(Rooms);
	}

	@DeleteMapping("{rtid}")
	public void delete(@PathVariable("rtid") Integer htid) {
		RoomService.delete(htid);
	}
}

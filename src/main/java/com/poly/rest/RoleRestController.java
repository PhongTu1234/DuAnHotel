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

import com.poly.entity.Places;
import com.poly.entity.Role;
import com.poly.Service.PlacesService;
import com.poly.Service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {

	@Autowired
	RoleService roleService;

	@GetMapping
	public List<Role> getAll() {
		return roleService.findAll();
	}

	@GetMapping("{id}")
	public Role getOne(@PathVariable("id") Integer id) {
		return roleService.findById(id);
	}

	@PostMapping
	public Role create(@RequestBody Role Places) {
		return roleService.create(Places);
	}

	@PutMapping("{id}")
	public Role update(@PathVariable("id") String id, @RequestBody Role Places) {
		return roleService.update(Places);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		roleService.delete(id);
	}

}

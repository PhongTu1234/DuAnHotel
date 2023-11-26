package com.poly.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.Service.AuthorityService;
import com.poly.entity.Authority;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {

	@Autowired
	AuthorityService AuthorityService;

	@GetMapping
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return AuthorityService.findAuthoritiesOfAdministrators();
		}
		return AuthorityService.findAll();
	}

	@PostMapping
	public Authority post(@RequestBody Authority auth) {
		return AuthorityService.create(auth);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		AuthorityService.delete(id);
	}
}

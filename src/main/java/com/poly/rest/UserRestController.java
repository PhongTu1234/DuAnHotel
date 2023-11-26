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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.Service.UserService;
import com.poly.entity.Users;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/users")
public class UserRestController {

	@Autowired
	UserService accountService;

	@GetMapping
	public Page<Users> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}

	@GetMapping("{cmt}")
	public Users getOne(@PathVariable("cmt") String id) {
		return accountService.findById(id);
	}

	@PostMapping
	public Users create(@RequestBody Users account) {
		return accountService.create(account);
	}

	@PutMapping("{cmt}")
	public Users update(@PathVariable("cmt") String id, @RequestBody Users account) {
		return accountService.update(account);
	}

	@DeleteMapping("{cmt}")
	public void delete(@PathVariable("cmt") String id) {
		accountService.delete(id);
	}
}

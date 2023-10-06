package com.poly.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.poly.entity.Users;
import com.poly.Service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

	@Autowired
	UserService accountService;

//	@GetMapping
//	public List<Users> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
//		if (admin.orElse(false)) {
//			return accountService.getAdministrators();
//		}
//		return accountService.findAll();
//	}

	@GetMapping("{cmt}")
	public Users getOne(@PathVariable("cmt") String cmt) {
		return accountService.findById(cmt);
	}

	@PostMapping
	public Users create(@RequestBody Users account) {
		return accountService.create(account);
	}

	@PutMapping("{cmt}")
	public Users update(@PathVariable("cmt") String cmt, @RequestBody Users account) {
		return accountService.update(account);
	}

	@DeleteMapping("{cmt}")
	public void delete(@PathVariable("cmt") String cmt) {
		accountService.delete(cmt);
	}
}

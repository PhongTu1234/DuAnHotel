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

import com.poly.Service.PaymentService;
import com.poly.entity.Payment;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/Payment")
public class PaymentRestController {

	@Autowired
	PaymentService HotelTypesService;

	@GetMapping
	public List<Payment> getAll() {
		return HotelTypesService.findAll();
	}

	@GetMapping("{id}")
	public Payment getOne(@PathVariable("id") Integer id) {
		return HotelTypesService.findById(id);
	}

	@PostMapping
	public Payment create(@RequestBody Payment Payment) {
		return HotelTypesService.create(Payment);
	}

	@PutMapping("{id}")
	public Payment update(@PathVariable("id") String id, @RequestBody Payment Payment) {
		return HotelTypesService.update(Payment);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		HotelTypesService.delete(id);
	}
}

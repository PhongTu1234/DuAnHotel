package com.poly.Service;

import java.util.List;

import com.poly.entity.Payment;

public interface PaymentService {
	List<Payment> findAll();

	Payment findById(Integer id);

	Payment create(Payment Payment);

	Payment update(Payment Payment);

	void delete(Integer id);
	List<Payment> findPageAdmin(Integer page, Integer number);
}

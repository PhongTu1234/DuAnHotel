package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Payment;
import com.poly.entity.Users;

public interface PaymentService {

	Payment findById(Integer id);

	Payment create(Payment Payment);

	Payment update(Payment Payment);

	void delete(Integer id);
	List<Payment> findPageAdmin(Integer page, Integer number);

	Payment findByPaymentName(Integer id);

	Page<Payment> findAll(Pageable page);
}

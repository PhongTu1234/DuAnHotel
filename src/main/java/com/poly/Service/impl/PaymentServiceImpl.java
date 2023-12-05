package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.PaymentDAO;
import com.poly.Service.PaymentService;
import com.poly.entity.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentDAO htdao;

	@Override
	public Payment findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public Payment create(Payment Payment) {
		return htdao.save(Payment);
	}

	@Override
	public Payment update(Payment Payment) {
		return htdao.save(Payment);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

	@Override
	public List<Payment> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public Payment findByPaymentName(Integer id) {
		// TODO Auto-generated method stub
		return htdao.findByPaymentName(id);
	}

	@Override
	public Page<Payment> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
	}

}

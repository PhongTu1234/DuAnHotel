package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.BookingsDAO;
import com.poly.Service.BookingsService;
import com.poly.entity.Booking_Room;
import com.poly.entity.Bookings;

@Service
public class BookingsServiceImpl implements BookingsService {

	@Autowired
	BookingsDAO htdao;

	@Override
	public Page<Bookings> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
	}


	@Override
	public Bookings findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public Bookings create(Bookings Bookings) {
		return htdao.save(Bookings);
	}

	@Override
	public Bookings update(Bookings Bookings) {
		return htdao.save(Bookings);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

	@Override
	public List<Bookings> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}



}

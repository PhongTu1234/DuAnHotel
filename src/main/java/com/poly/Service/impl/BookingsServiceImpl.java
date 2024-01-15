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
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public Page<Bookings> findAlla(Pageable page) {
		return null;
	}


	@Override
	public Bookings findByBookingID(Integer id) {
		return null;
	}


	@Override
	public List<Bookings> findByStatusIsTrue(String cmt) {
		return htdao.findByStatusIsTrue(cmt);
	}


	@Override
	public Bookings findByIds(Integer id) {
		return htdao.findByIds(id);
	}


	@Override
	public Bookings checkStatus(String cmt) {
		return htdao.checkStatus(cmt);
	}

}

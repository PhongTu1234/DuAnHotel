package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.Booking_RoomDAO;
import com.poly.Service.Booking_RoomService;
import com.poly.entity.Booking_Room;

@Service
public class Booking_RoomServiceImpl implements Booking_RoomService {

	@Autowired
	Booking_RoomDAO htdao;

	@Override
	public List<Booking_Room> findAll() {
		return htdao.findAll();
	}

	@Override
	public Booking_Room findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public Booking_Room create(Booking_Room Booking_Room) {
		return htdao.save(Booking_Room);
	}

	@Override
	public Booking_Room update(Booking_Room Booking_Room) {
		return htdao.save(Booking_Room);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

	@Override
	public List<Booking_Room> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}
	
	public List<Booking_Room> getBookingDetailsForUser(String user) {
		return htdao.getBookingDetailsForUser(user);
	}

	@Override
	public Page<Booking_Room> findAlla(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
	}

}

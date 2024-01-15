package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.Booking_RoomDAO;
import com.poly.Service.Booking_RoomService;
import com.poly.entity.Blogs;
import com.poly.entity.Booking_Room;
import com.poly.entity.Bookings;

@Service
public class Booking_RoomServiceImpl implements Booking_RoomService {

	@Autowired
	Booking_RoomDAO htdao;


	@Override
	public Page<Booking_Room> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
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
	public Page<Booking_Room> adfindByHotelId(Integer hid, Pageable page) {
		// TODO Auto-generated method stub
		return htdao.adfindByHotelId(hid, page);
	}

	@Override
	public List<Booking_Room> findByIds(Integer id) {
		return htdao.findByIds(id);
	}

}

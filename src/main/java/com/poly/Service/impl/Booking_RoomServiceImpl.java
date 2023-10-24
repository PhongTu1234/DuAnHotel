package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}

package com.poly.Service;

import java.util.List;

import com.poly.entity.Booking_Room;

public interface Booking_RoomService {
	List<Booking_Room> findAll();

	Booking_Room findById(Integer id);

	Booking_Room create(Booking_Room Booking_Room);

	Booking_Room update(Booking_Room Booking_Room);

	void delete(Integer id);
	
	List<Booking_Room> getBookingDetailsForUser(String user);
}

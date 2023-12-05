package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Booking_Room;
import com.poly.entity.Bookings;

public interface Booking_RoomService {
	Page<Booking_Room> findAll(Pageable page);
	
	Booking_Room findById(Integer id);

	Booking_Room create(Booking_Room Booking_Room);

	Booking_Room update(Booking_Room Booking_Room);

	void delete(Integer id);
	
	List<Booking_Room> findPageAdmin(Integer page, Integer number);

	List<Booking_Room> getBookingDetailsForUser(String user);
	
}

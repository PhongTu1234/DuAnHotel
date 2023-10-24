package com.poly.Service;

import java.util.List;

import com.poly.entity.Bookings;

public interface BookingsService {
	List<Bookings> findAll();

	Bookings findById(Integer id);

	Bookings create(Bookings Bookings);

	Bookings update(Bookings Bookings);

	void delete(Integer id);
}

package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Blogs;
import com.poly.entity.Bookings;

public interface BookingsService {
	Page<Bookings> findAll(Pageable page);
	
	Bookings findById(Integer id);

	Bookings create(Bookings Bookings);

	Bookings update(Bookings Bookings);

	void delete(Integer id);

	List<Bookings> findPageAdmin(Integer page, Integer number);
	
}

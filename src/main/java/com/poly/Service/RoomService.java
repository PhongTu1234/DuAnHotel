package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Hotels;
import com.poly.entity.Rooms;

public interface RoomService {

	Rooms findById(Integer id);

	List<Rooms> findByRoomTypesId(String cid);

	Rooms create(Rooms room);

	Rooms update(Rooms room);

	void delete(Integer id);

	List<Rooms> findByHotelId(Integer hid);
	
	Page<Rooms> adfindByHotelId(Integer hid, Pageable page);

	List<Rooms> findByRoom1to8();

	List<Rooms> findPageAdmin(Integer page, Integer number);
	
	Rooms findByRoomName(String name);
	
	Page<Rooms> findAll(Pageable page);
	
	List<Rooms> findTop8ByOrderByRatingDesc();
	
	List<Rooms> findTop8RoomsByTotalBookingsAndPaymentStatus();
	
	Page<Hotels> findHotelAndRoomType(Pageable page);

}
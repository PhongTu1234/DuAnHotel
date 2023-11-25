package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Hotels;
import com.poly.entity.RoomTypes;

public interface RoomTypesService {
	List<RoomTypes> findAll();

	RoomTypes findById(Integer id);

	RoomTypes create(RoomTypes Roomtypes);

	RoomTypes update(RoomTypes Roomtypes);

	void delete(Integer id);
	
	List<RoomTypes> findPageAdmin(Integer page, Integer number);

	RoomTypes findByRoomtypeName(String name);
	
	Page<RoomTypes> findAlla(Pageable page);

}

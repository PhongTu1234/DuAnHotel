package com.poly.Service;

import java.util.List;

import com.poly.entity.RoomTypes;

public interface RoomTypesService {
	List<RoomTypes> findAll();

	RoomTypes findById(Integer id);

	RoomTypes create(RoomTypes Roomtypes);

	RoomTypes update(RoomTypes Roomtypes);

	void delete(Integer id);
	
	List<RoomTypes> findPageAdmin(Integer page, Integer number);

	RoomTypes findByRoomtypeName(String name);
}
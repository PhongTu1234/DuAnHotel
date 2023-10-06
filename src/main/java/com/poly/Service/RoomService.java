package com.poly.Service;

import java.util.List;

import com.poly.entity.Rooms;

public interface RoomService {
	List<Rooms> findAll();

	Rooms findById(Integer id);

	List<Rooms> findByRoomTypesId(String cid);

	Rooms create(Rooms room);

	Rooms update(Rooms room);

	void delete(Integer id);

}
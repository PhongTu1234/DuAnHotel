package com.poly.Service;

import java.util.List;

import com.poly.entity.Service_Rooms;

public interface Service_RoomService {
	List<Service_Rooms> findAll();

	Service_Rooms findById(Integer id);

	Service_Rooms create(Service_Rooms Service_Room);

	Service_Rooms update(Service_Rooms Service_Room);

	void delete(Integer id);
}

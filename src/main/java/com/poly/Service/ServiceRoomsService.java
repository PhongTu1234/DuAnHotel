package com.poly.Service;

import java.util.List;

import com.poly.entity.ServiceRooms;

public interface ServiceRoomsService {
	List<ServiceRooms> findAll();

	ServiceRooms findById(Integer id);

	ServiceRooms create(ServiceRooms Service_Room);

	ServiceRooms update(ServiceRooms Service_Room);

	void delete(Integer id);

	List<ServiceRooms> findPageAdmin(Integer page, Integer number);
	
}

package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Hotels;
import com.poly.entity.ServiceRooms;

public interface ServiceRoomsService {


	ServiceRooms findById(Integer id);

	ServiceRooms create(ServiceRooms Service_Room);

	ServiceRooms update(ServiceRooms Service_Room);

	void delete(Integer id);

	List<ServiceRooms> findPageAdmin(Integer page, Integer number);
	
	Page<ServiceRooms> findAll(Pageable page);

	
}

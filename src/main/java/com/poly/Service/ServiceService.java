package com.poly.Service;

import java.util.List;

import com.poly.entity.Services;

public interface ServiceService {
	List<Services> findAll();

	Services findById(Integer id);

	Services create(Services Service_Room);

	Services update(Services Service_Room);

	void delete(Integer id);
	
}

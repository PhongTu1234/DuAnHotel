package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Hotels;
import com.poly.entity.Services;

public interface ServiceService {

	Services findById(Integer id);

	Services create(Services Service_Room);

	Services update(Services Service_Room);

	void delete(Integer id);

	List<Services> findPageAdmin(Integer page, Integer number);
	
	Services findByServiceName(String name);
	
	Page<Services> findAll(Pageable page);
	
	List<Services> findShop();

}

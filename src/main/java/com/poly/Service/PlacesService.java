package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Hotels;
import com.poly.entity.Payment;
import com.poly.entity.Places;

public interface PlacesService {

	Places findById(Integer id);

	Places create(Places Places);

	Places update(Places Places);

	void delete(Integer id);

	Places findPlaceWithMostHotels();

	Places findPlaceWithMostHotelsTop2();

	Places findPlaceWithMostHotelsTop3();

	Places findPlaceWithMostHotelsTop4();
	
	List<Places> findPageAdmin(Integer page, Integer number);
	
	Places findByPlaceName(String name);
	
	Page<Places> findAll(Pageable page);
}

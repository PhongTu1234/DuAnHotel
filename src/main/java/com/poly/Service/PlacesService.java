package com.poly.Service;

import java.util.List;

import com.poly.entity.Places;

public interface PlacesService {
	List<Places> findAll();

	Places findById(Integer id);

	Places create(Places Places);

	Places update(Places Places);

	void delete(Integer id);

	Places findPlaceWithMostHotels();

	Places findPlaceWithMostHotelsTop2();

	Places findPlaceWithMostHotelsTop3();

	Places findPlaceWithMostHotelsTop4();
}

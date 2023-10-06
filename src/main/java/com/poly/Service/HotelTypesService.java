package com.poly.Service;

import java.util.List;

import com.poly.entity.HotelTypes;

public interface HotelTypesService {
	List<HotelTypes> findAll();

	HotelTypes findById(String id);

	HotelTypes create(HotelTypes hoteltypes);

	HotelTypes update(HotelTypes hoteltypes);

	void delete(String id);
}

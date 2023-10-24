package com.poly.Service;

import java.util.List;

import com.poly.entity.Images_Hotel;

public interface Images_HotelService {
	List<Images_Hotel> findAll();

	Images_Hotel findById(Integer id);

	Images_Hotel create(Images_Hotel Images_Hotel);

	Images_Hotel update(Images_Hotel Images_Hotel);

	void delete(Integer id);
}

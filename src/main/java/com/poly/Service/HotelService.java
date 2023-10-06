package com.poly.Service;

import java.util.List;

import com.poly.entity.Hotels;

public interface HotelService {
	List<Hotels> findAll();

	Hotels findById(Integer id);

	List<Hotels> findByHotelTypesId(String cid);

	Hotels create(Hotels product);

	Hotels update(Hotels product);

	void delete(Integer id);

}
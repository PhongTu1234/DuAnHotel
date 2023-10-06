package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.HotelTypesDAO;
import com.poly.entity.HotelTypes;
import com.poly.Service.HotelTypesService;

@Service
public class HotelTypesServiceImpl implements HotelTypesService {

	@Autowired
	HotelTypesDAO htdao;

	@Override
	public List<HotelTypes> findAll() {
		return htdao.findAll();
	}

	@Override
	public HotelTypes findById(String id) {
		return htdao.findById(id).get();
	}

	public HotelTypes create(HotelTypes HotelTypes) {
		return htdao.save(HotelTypes);
	}

	@Override
	public HotelTypes update(HotelTypes HotelTypes) {
		return htdao.save(HotelTypes);
	}

	@Override
	public void delete(String id) {
		htdao.deleteById(id);
	}

}

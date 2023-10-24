package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.Images_HotelDAO;
import com.poly.Service.Images_HotelService;
import com.poly.entity.Images_Hotel;

@Service
public class Images_HotelServiceImpl implements Images_HotelService {

	@Autowired
	Images_HotelDAO htdao;

	@Override
	public List<Images_Hotel> findAll() {
		return htdao.findAll();
	}

	@Override
	public Images_Hotel findById(Integer id) {
		return htdao.findById(id).get();
	}

	public Images_Hotel create(Images_Hotel Images_Hotel) {
		return htdao.save(Images_Hotel);
	}

	@Override
	public Images_Hotel update(Images_Hotel Images_Hotel) {
		return htdao.save(Images_Hotel);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

}

package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.HotelDAO;
import com.poly.entity.Hotels;
import com.poly.Service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	HotelDAO hdao;

	@Override
	public List<Hotels> findAll() {
		return hdao.findAll();
	}

	@Override
	public Hotels findById(Integer id) {
		return hdao.findById(id).get();
	}

	@Override
	public List<Hotels> findByHotelTypesId(String cid) {
		return hdao.findByHotelTypesId(cid);
	}

	public Hotels create(Hotels Hotels) {
		return hdao.save(Hotels);
	}

	@Override
	public Hotels update(Hotels Hotels) {
		return hdao.save(Hotels);
	}

	@Override
	public void delete(Integer id) {
		hdao.deleteById(id);
	}

}

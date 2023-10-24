package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.PlacesDAO;
import com.poly.Service.PlacesService;
import com.poly.entity.Places;

@Service
public class PlacesServiceImpl implements PlacesService {

	@Autowired
	PlacesDAO htdao;

	@Override
	public List<Places> findAll() {
		return htdao.findAll();
	}

	@Override
	public Places findById(Integer id) {
		return htdao.findById(id).get();
	}

	public Places create(Places Places) {
		return htdao.save(Places);
	}

	@Override
	public Places update(Places Places) {
		return htdao.save(Places);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

}

package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.ServiceDAO;
import com.poly.Service.ServiceService;
import com.poly.entity.Services;

@Service
public class ServicesServiceImpl implements ServiceService {

	@Autowired
	ServiceDAO htdao;

	@Override
	public List<Services> findAll() {
		return htdao.findAll();
	}

	@Override
	public Services findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public Services create(Services Services) {
		return htdao.save(Services);
	}

	@Override
	public Services update(Services Services) {
		return htdao.save(Services);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

}

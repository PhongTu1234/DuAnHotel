package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.ServiceDAO;
import com.poly.Service.ServiceService;
import com.poly.entity.Services;

@Service
public class ServicesServiceImpl implements ServiceService {

	@Autowired
	ServiceDAO htdao;

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

	@Override
	public List<Services> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public Services findByServiceName(String name) {
		// TODO Auto-generated method stub
		return htdao.findByServiceName(name);
	}

	@Override
	public Page<Services> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
	}

	@Override
	public List<Services> findShop() {
		// TODO Auto-generated method stub
		return htdao.findShop();
	}

}

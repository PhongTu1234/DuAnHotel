package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.PlacesDAO;
import com.poly.Service.PlacesService;
import com.poly.entity.Places;

@Service
public class PlacesServiceImpl implements PlacesService {

	@Autowired
	PlacesDAO htdao;

	@Override
	public Page<Places> findAll(Pageable page) {
		return htdao.findAll(page);
	}

	@Override
	public Places findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
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

	@Override
	public Places findPlaceWithMostHotels() {
		// TODO Auto-generated method stub
		return htdao.findPlaceWithMostHotels();
	}

	@Override
	public Places findPlaceWithMostHotelsTop2() {
		// TODO Auto-generated method stub
		return htdao.findPlaceWithMostHotelsTop2();
	}

	@Override
	public Places findPlaceWithMostHotelsTop3() {
		// TODO Auto-generated method stub
		return htdao.findPlaceWithMostHotelsTop3();
	}

	@Override
	public Places findPlaceWithMostHotelsTop4() {
		// TODO Auto-generated method stub
		return htdao.findPlaceWithMostHotelsTop4();
	}

	@Override
	public List<Places> findPageAdmin(Integer page, Integer number) {
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public Places findByPlaceName(String name) {
		// TODO Auto-generated method stub
		return htdao.findByPlaceName(name);
	}

	@Override
	public List<Places> findShop() {
		// TODO Auto-generated method stub
		return htdao.findShop();
	}


}

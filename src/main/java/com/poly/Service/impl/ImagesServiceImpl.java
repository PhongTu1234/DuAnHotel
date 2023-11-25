package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.ImagesDAO;
import com.poly.Service.ImagesService;
import com.poly.entity.Images;

@Service
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	ImagesDAO htdao;

	@Override
	public List<Images> findAll() {
		return htdao.findAll();
	}

	@Override
	public Images findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public Images create(Images Images) {
		return htdao.save(Images);
	}

	@Override
	public Images update(Images Images) {
		return htdao.save(Images);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

	@Override
	public List<Images> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public Images findByImageName(String name) {
		// TODO Auto-generated method stub
		return htdao.findByImageName(name);
	}

	@Override
	public Page<Images> findAlla(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
	}

}

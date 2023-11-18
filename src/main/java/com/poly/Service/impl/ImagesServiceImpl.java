package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}

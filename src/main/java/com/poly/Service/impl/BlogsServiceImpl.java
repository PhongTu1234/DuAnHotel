package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.BlogsDAO;
import com.poly.Service.BlogsService;
import com.poly.entity.Blogs;
import com.poly.entity.Hotels;

@Service
public class BlogsServiceImpl implements BlogsService {

	@Autowired
	BlogsDAO htdao;

	@Override
	public Page<Blogs> findAll(Pageable page) {
		return htdao.findAll(page);
	}

	@Override
	public Blogs findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public Blogs create(Blogs Blogs) {
		return htdao.save(Blogs);
	}

	@Override
	public Blogs update(Blogs Blogs) {
		return htdao.save(Blogs);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

	@Override
	public List<Blogs> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public List<Blogs> findShop() {
		// TODO Auto-generated method stub
		return htdao.findShop();
	}

}

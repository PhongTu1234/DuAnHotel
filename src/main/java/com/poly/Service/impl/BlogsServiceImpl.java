package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.BlogsDAO;
import com.poly.Service.BlogsService;
import com.poly.entity.Blogs;

@Service
public class BlogsServiceImpl implements BlogsService {

	@Autowired
	BlogsDAO htdao;

	@Override
	public List<Blogs> findAll() {
		return htdao.findAll();
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

}

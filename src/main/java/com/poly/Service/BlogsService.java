package com.poly.Service;

import java.util.List;

import com.poly.entity.Blogs;

public interface BlogsService {
	List<Blogs> findAll();

	Blogs findById(Integer id);

	Blogs create(Blogs Blogs);

	Blogs update(Blogs Blogs);

	void delete(Integer id);

	List<Blogs> findPageAdmin(Integer page, Integer number);
	
}

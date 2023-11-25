package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Blogs;
import com.poly.entity.Hotels;

public interface BlogsService {
	List<Blogs> findAll();

	Blogs findById(Integer id);

	Blogs create(Blogs Blogs);

	Blogs update(Blogs Blogs);

	void delete(Integer id);

	List<Blogs> findPageAdmin(Integer page, Integer number);
	
	Page<Blogs> findAlla(Pageable page);
}

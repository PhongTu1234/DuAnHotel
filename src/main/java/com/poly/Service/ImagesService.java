package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Images;
import com.poly.entity.Users;

public interface ImagesService {
	List<Images> findAll();

	Images findById(Integer id);

	Images create(Images Images);

	Images update(Images Images);

	void delete(Integer id);
	
	List<Images> findPageAdmin(Integer page, Integer number);
	
	Images findByImageName(String name);
	
	Page<Images> findAlla(Pageable page);

}

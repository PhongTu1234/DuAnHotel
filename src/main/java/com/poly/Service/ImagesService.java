package com.poly.Service;

import java.util.List;

import com.poly.entity.Images;

public interface ImagesService {
	List<Images> findAll();

	Images findById(Integer id);

	Images create(Images Images);

	Images update(Images Images);

	void delete(Integer id);
}

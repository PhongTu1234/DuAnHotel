package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Feedback;
import com.poly.entity.Hotels;

public interface FeedbackService {
	Feedback findById(Integer id);

	Feedback create(Feedback Feedback);

	Feedback update(Feedback Feedback);

	void delete(Integer id);

	List<Feedback> findPageAdmin(Integer page, Integer number);
	
	Page<Feedback> findAll(Pageable page);
}

package com.poly.Service;

import java.util.List;

import com.poly.entity.Feedback;

public interface FeedbackService {
	List<Feedback> findAll();

	Feedback findById(Integer id);

	Feedback create(Feedback Feedback);

	Feedback update(Feedback Feedback);

	void delete(Integer id);

	List<Feedback> findPageAdmin(Integer page, Integer number);
	
}

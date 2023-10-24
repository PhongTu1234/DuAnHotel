package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.FeedbackDAO;
import com.poly.Service.FeedbackService;
import com.poly.entity.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackDAO htdao;

	@Override
	public List<Feedback> findAll() {
		return htdao.findAll();
	}

	@Override
	public Feedback findById(Integer id) {
		return htdao.findById(id).get();
	}

	public Feedback create(Feedback Feedback) {
		return htdao.save(Feedback);
	}

	@Override
	public Feedback update(Feedback Feedback) {
		return htdao.save(Feedback);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

}

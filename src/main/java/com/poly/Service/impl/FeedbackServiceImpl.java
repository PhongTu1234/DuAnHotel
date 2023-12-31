package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.FeedbackDAO;
import com.poly.Service.FeedbackService;
import com.poly.entity.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackDAO htdao;


	@Override
	public Feedback findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
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

	@Override
	public List<Feedback> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public Page<Feedback> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
	}

}

package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Feedback;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer> {

}

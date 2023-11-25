package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Feedback;
import com.poly.entity.Hotels;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer> {
	@Query(value = "SELECT * FROM Feedback h  order by h.feed_back_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Feedback> findPageAdmin(Integer page, Integer number);
	
	Page<Feedback> findAll(org.springframework.data.domain.Pageable page);
}

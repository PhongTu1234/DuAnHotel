package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Blogs;
import com.poly.entity.Bookings;

public interface BookingsDAO extends JpaRepository<Bookings, Integer> {
	@Query(value = "SELECT * FROM Bookings h  order by h.booking_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Bookings> findPageAdmin(Integer page, Integer number);
	
	Page<Bookings> findAll(Pageable page);
}

package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Bookings;
import com.poly.entity.Images;
import com.poly.entity.Services;

public interface ImagesDAO extends JpaRepository<Images, Integer> {
	@Query(value = "SELECT * FROM Images h  order by h.image_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Images> findPageAdmin(Integer page, Integer number);
	
	@Query("SELECT p FROM Services p where p.name = ?1")
	Images findByImageName(String name);
	
	Page<Images> findAll(org.springframework.data.domain.Pageable page);

}

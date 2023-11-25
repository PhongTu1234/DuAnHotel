package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Blogs;
import com.poly.entity.Hotels;

public interface BlogsDAO extends JpaRepository<Blogs, Integer> {
	@Query(value = "SELECT * FROM blogs h  order by h.id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Blogs> findPageAdmin(Integer page, Integer number);
	
	Page<Blogs> findAll(org.springframework.data.domain.Pageable page);
}

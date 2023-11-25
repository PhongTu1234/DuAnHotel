package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.RoomTypes;
import com.poly.entity.Rooms;
import com.poly.entity.Services;

public interface ServiceDAO extends JpaRepository<Services, Integer> {
	@Query(value = "SELECT * FROM Services h  order by h.service_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Services> findPageAdmin(Integer page, Integer number);
	
	@Query("SELECT p FROM Services p where p.name = ?1")
	Services findByServiceName(String name);
	
	Page<Services> findAll(org.springframework.data.domain.Pageable page);
}

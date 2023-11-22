package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.ServiceRooms;


public interface ServiceRoomsDAO extends JpaRepository<ServiceRooms, Integer> {
	@Query(value = "SELECT * FROM service_rooms h  order by h.id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<ServiceRooms> findPageAdmin(Integer page, Integer number);
}

package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.RoomTypes;
import com.poly.entity.Rooms;

public interface RoomTypesDAO extends JpaRepository<RoomTypes, Integer> {
//	@Query(value = "SELECT count(c.id) FROM Categories c", nativeQuery = true)
//	Integer countAllCategory();
	@Query(value = "SELECT * FROM RoomTypes h  order by h.room_type_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<RoomTypes> findPageAdmin(Integer page, Integer number);
	
	@Query("SELECT p FROM RoomTypes p where p.name = ?1")
	RoomTypes findByRoomtypeName(String name);
	
	Page<RoomTypes> findAll(org.springframework.data.domain.Pageable page);
}

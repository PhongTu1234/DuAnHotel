package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Rooms;

public interface RoomDAO extends JpaRepository<Rooms, Integer> {
	@Query("SELECT p FROM Rooms p WHERE p.rt_id.room_type_id=?1")
	List<Rooms> findByRoomTypesId(String htid);

//	@Query(value = "SELECT count(p.id) FROM Products p", nativeQuery = true)
//	Integer countAllProduct();
}
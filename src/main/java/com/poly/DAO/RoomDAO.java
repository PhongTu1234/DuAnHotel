package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Rooms;

public interface RoomDAO extends JpaRepository<Rooms, Integer> {
	@Query("SELECT p FROM Rooms p WHERE p.RoomTypes.id=?1")
	List<Rooms> findByRoomTypesId(String htid);
	
	@Query("SELECT p FROM Rooms p")
	List<Rooms> findAll();

	@Query(value = "SELECT count(p.id) FROM Products p", nativeQuery = true)
	Integer countAllProduct();
	
	@Query("SELECT p FROM Rooms p WHERE p.Hotels.id=?1")
	List<Rooms> findByHotelId(Integer hid);
	
	@Query("SELECT p FROM Rooms p WHERE p.id between 1513 and 1520")
	List<Rooms> findByRoom1to8();
}

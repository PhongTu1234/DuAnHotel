package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Rooms;
import com.poly.entity.Services;

public interface RoomDAO extends JpaRepository<Rooms, Integer> {
	@Query("SELECT p FROM Rooms p WHERE p.RoomTypes.id=?1")
	List<Rooms> findByRoomTypesId(String htid);

//	@Override
//	@Query("SELECT p FROM Rooms p")
//	List<Rooms> findAll();
	
	Page<Rooms> findAll(org.springframework.data.domain.Pageable page);

	@Query(value = "SELECT count(p.id) FROM Products p", nativeQuery = true)
	Integer countAllProduct();

	@Query("SELECT p FROM Rooms p WHERE p.Hotels.id=?1")
	List<Rooms> findByHotelId(Integer hid);

	@Query("SELECT p FROM Rooms p WHERE p.id between 1513 and 1520")
	List<Rooms> findByRoom1to8();
	
//	@Query(value = "SELECT * FROM Rooms h  order by h.room_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
//	List<Rooms> findPageAdmin(Integer page, Pageable page);
	
	@Query("SELECT p FROM Rooms p where p.name = ?1")
	Rooms findByRoomName(String name);
	
	
}

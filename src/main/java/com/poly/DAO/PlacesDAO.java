package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Hotels;
import com.poly.entity.Payment;
import com.poly.entity.Places;

public interface PlacesDAO extends JpaRepository<Places, Integer> {

	@Query(value = "SELECT TOP 1 p.place_id, p.place_name " + "FROM Place p "
			+ "JOIN Hotels h ON p.place_id = h.place_id " + "GROUP BY p.place_id, p.place_name "
			+ "ORDER BY COUNT(h.hotel_id) DESC", nativeQuery = true)
	Places findPlaceWithMostHotels();

	@Query(value = "SELECT p.place_id, p.place_name  " + "FROM Place p " + "JOIN Hotels h ON p.place_id = h.place_id "
			+ "GROUP BY p.place_id, p.place_name " + "ORDER BY COUNT(h.hotel_id) DESC "
			+ "OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
	Places findPlaceWithMostHotelsTop2();

	@Query(value = "SELECT p.place_id, p.place_name " + "FROM Place p " + "JOIN Hotels h ON p.place_id = h.place_id "
			+ "GROUP BY p.place_id, p.place_name " + "ORDER BY COUNT(h.hotel_id) DESC "
			+ "OFFSET 2 ROWS FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
	Places findPlaceWithMostHotelsTop3();

	@Query(value = "SELECT p.place_id, p.place_name " + "FROM Place p " + "JOIN Hotels h ON p.place_id = h.place_id "
			+ "GROUP BY p.place_id, p.place_name " + "ORDER BY COUNT(h.hotel_id) DESC "
			+ "OFFSET 3 ROWS FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
	Places findPlaceWithMostHotelsTop4();
	
	@Query(value = "SELECT * FROM Place h  order by h.place_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Places> findPageAdmin(Integer page, Integer number);
	
	@Query("SELECT p FROM Places p where p.name = ?1")
	Places findByPlaceName(String name);
	
	Page<Places> findAll(Pageable page);
	
	@Query("SELECT p FROM Places p")
	List<Places> findShop();
}

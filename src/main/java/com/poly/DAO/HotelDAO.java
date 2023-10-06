package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Hotels;

public interface HotelDAO extends JpaRepository<Hotels, Integer> {
	@Query("SELECT p FROM Hotels p WHERE p.ht_id.hotel_type_id=?1")
	List<Hotels> findByHotelTypesId(String htid);

//	@Query(value = "SELECT count(p.id) FROM Products p", nativeQuery = true)
//	Integer countAllProduct();
}

package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.HotelTypes;

public interface HotelTypesDAO extends JpaRepository<HotelTypes, Integer> {
	@Query(value = "SELECT ht from HotelTypes ht WHERE ht.hotelLevel BETWEEN 1 and 2")
	List<HotelTypes> getHotelType1to2();
	
	
}

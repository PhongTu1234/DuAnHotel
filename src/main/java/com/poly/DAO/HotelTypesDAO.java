package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.HotelTypes;

public interface HotelTypesDAO extends JpaRepository<HotelTypes, String> {
//	@Query(value = "SELECT count(c.id) FROM Categories c", nativeQuery = true)
//	Integer countAllCategory();
//	@Query(value = "SELECT hotel_level FROM HoteTypes where hotel_level between 1.0 and 2.0")
//	List<HotelTypes> getHotelLevel1to2();
//	@Query(value = "SELECT hotel_level FROM HOTELTYPES where hotel_level between 2.0 and 3.0")
//	Integer getHotelLevel2to3();
//	@Query(value = "SELECT hotel_level FROM HOTELTYPES where hotel_level between 3.0 and 4.0")
//	Integer getHotelLevel3to4();
//	@Query(value = "SELECT hotel_level FROM HOTELTYPES where hotel_level between 4.0 and 5.0")
//	Integer getHotelLevel4to5();
//	@Query(value = "SELECT hotel_level FROM HOTELTYPES where hotel_level = 5.0")
//	Integer getHotelLevel5();
}

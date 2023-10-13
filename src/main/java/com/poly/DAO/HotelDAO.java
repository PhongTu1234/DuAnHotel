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

	
	@Query(value = "SELECT  hotels.hotel_name from HOTELTYPES join Hotels on Hotels.hotel_type_id= HOTELTYPES.hotel_type_id where hotel_level between 1.0 and 2.0", nativeQuery = true )
	List<Hotels> findHotelByHotelLevel1to2();
	@Query(value = "SELECT  hotels.hotel_name from HOTELTYPES join Hotels on Hotels.hotel_type_id= HOTELTYPES.hotel_type_id where hotel_level between 2.0 and 3.0", nativeQuery = true)
	List<Hotels> findHotelByHotelLevel2to3();
	@Query(value = "SELECT  hotels.hotel_name from HOTELTYPES join Hotels on Hotels.hotel_type_id= HOTELTYPES.hotel_type_id where hotel_level between 3.0 and 4.0", nativeQuery = true)
	List<Hotels> findHotelByHotelLevel3to4();
	@Query(value = "SELECT  hotels.hotel_name from HOTELTYPES join Hotels on Hotels.hotel_type_id= HOTELTYPES.hotel_type_id where hotel_level between 4.0 and 5.0", nativeQuery = true)
	List<Hotels> findHotelByHotelLevel4to5();
	@Query(value = "SELECT  hotels.hotel_name from HOTELTYPES join Hotels on Hotels.hotel_type_id= HOTELTYPES.hotel_type_id where hotel_level = 5.0", nativeQuery = true)
	List<Hotels> findHotelByHotelLevel5();
	
	
	
}

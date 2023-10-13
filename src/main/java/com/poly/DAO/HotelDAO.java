package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Hotels;

public interface HotelDAO extends JpaRepository<Hotels, Integer> {
	@Query("SELECT p FROM Hotels p WHERE p.ht_id.hotel_type_id=?1")
	List<Hotels> findByHotelTypesId(String htid);
	
	//List<Hotels> findByHotelTypeHotelLevelBetween(String startLevel, String endLevel);

//	@Query(value = "SELECT count(p.id) FROM Products p", nativeQuery = true)
//	Integer countAllProduct();
	
	@Query("SELECT h FROM Hotels h WHERE h.ht_id.hotelLevel BETWEEN 0 and 1")
	List<Hotels> findHotelByHotelLevel0to1();

	@Query("SELECT h FROM Hotels h WHERE h.ht_id.hotelLevel BETWEEN 1 and 2")
	List<Hotels> findHotelByHotelLevel1to2();
	
	@Query("SELECT h FROM Hotels h WHERE h.ht_id.hotelLevel BETWEEN 2 and 3")
	List<Hotels> findHotelByHotelLevel2to3();
	
	@Query("SELECT h FROM Hotels h WHERE h.ht_id.hotelLevel BETWEEN 3 and 4")
	List<Hotels> findHotelByHotelLevel3to4();
	
	@Query("SELECT h FROM Hotels h WHERE h.ht_id.hotelLevel BETWEEN 4 and 5")
	List<Hotels> findHotelByHotelLevel4to5();
	
	@Query("SELECT h FROM Hotels h WHERE h.ht_id.hotelLevel = 5")
	List<Hotels> findHotelByHotelLevel5();
	
}

package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Hotels;

public interface HotelDAO extends JpaRepository<Hotels, Integer> {
	@Query("SELECT p FROM Hotels p WHERE p.Place.id= ?1")
	List<Hotels> findByPlaceId(Integer id);
	
	@Query("SELECT h FROM Hotels h WHERE h.Level between ?1 and ?2 AND h.Place.id=?3")
	List<Hotels> findHotelByHotelLevelstarttoend(Integer start, Integer end, Integer placeId);

	@Query("SELECT h FROM Hotels h WHERE h.Level IN (1,2)")
	List<Hotels> findHotelByHotelLevel1to2();
	
	@Query("SELECT h FROM Hotels h WHERE h.Level IN (2,3)")
	List<Hotels> findHotelByHotelLevel2to3();
	
	@Query("SELECT h FROM Hotels h WHERE h.Level IN (3,4)")
	List<Hotels> findHotelByHotelLevel3to4();
	
	@Query("SELECT h FROM Hotels h WHERE h.Level IN (4,5)")
	List<Hotels> findHotelByHotelLevel4to5();
	
	@Query("SELECT h FROM Hotels h WHERE h.Level = 5")
	List<Hotels> findHotelByHotelLevel5();
	
	
//	@Query(value = "SELECT * FROM Hotels h WHERE h.hotel_id > ?1  order by h.hotel_id OFFSET 0 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	@Query(value = "SELECT * FROM Hotels h  order by h.hotel_id OFFSET ?1 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	List<Hotels> findPage(Integer page);
	
	@Query(value = "select * from Hotels where hotels.hotel_level = ?1  and Hotels.place_id = ?2"
			+ "						order by hotels.hotel_id OFFSET ?3 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	List<Hotels> findHotelByPlaceidAndStart(Integer start, Integer placeId, Integer Page);
	
//	@Query("select count(h.id) from Hotels h")
//	Integer findCountId();
	
//    @Query(value = "SELECT TOP 1 * " +
//            "FROM Hotels ", nativeQuery = true)
//    List<Hotels> findHotelsInLocationWithMostHotels();
}

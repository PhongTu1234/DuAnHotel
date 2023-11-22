package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Hotels;
import com.poly.entity.RoomTypes;

public interface HotelDAO extends JpaRepository<Hotels, Integer> {
	@Query("SELECT p FROM Hotels p WHERE p.Place.id= ?1")
	List<Hotels> findByPlaceId(Integer id);

	@Query("SELECT h FROM Hotels h WHERE h.Level between ?1 and ?2 AND h.Place.id=?3")
	List<Hotels> findHotelByHotelLevelstarttoend(Integer start, Integer end, Integer placeId);



	@Query("SELECT h FROM Hotels h WHERE h.Level = ?1")
	List<Hotels> findHotelByHotelLevel(Integer level);
	
	
	
//	@Query(value = "SELECT * FROM Hotels h WHERE h.hotel_id > ?1  order by h.hotel_id OFFSET 0 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	@Query(value = "SELECT * FROM Hotels h  order by h.hotel_id OFFSET ?1 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	List<Hotels> findPage(Integer page);

	@Query(value = "select * from Hotels where Hotels.place_id = ?1"
			+ "						order by hotels.hotel_id OFFSET ?2 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	List<Hotels> findHotelByPlaceid(Integer id, Integer page);
	
	@Query(value =  "select * from Hotels where hotels.hotel_level = ?1"
			+ "						order by hotels.hotel_id OFFSET ?2 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	List<Hotels> findHotelByLevel(Integer level, Integer page);
	
	@Query(value =  "SELECT DISTINCT h.* "
			+ "FROM Hotels h "
			+ "JOIN Rooms r ON h.hotel_id = r.hotel_id "
			+ "JOIN ServiceRooms sr ON r.room_id = sr.room_id "
			+ "JOIN [Services] s ON sr.service_id = s.service_id "
			+ "WHERE s.service_id = 2 "
			+ "order by hotels.hotel_id OFFSET ?2 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	List<Hotels> findHotelByService(Integer service, Integer page);
	
	
	
	@Query(value = "select * from Hotels where hotels.hotel_level = ?1  and Hotels.place_id = ?2"
			+ "						order by hotels.hotel_id OFFSET ?3 ROWS FETCH NEXT 15 ROWS only", nativeQuery = true)
	List<Hotels> findHotelByPlaceidAndStart(Integer start, Integer placeId, Integer Page);

//	@Query("select count(h.id) from Hotels h")
//	Integer findCountId();

//    @Query(value = "SELECT TOP 1 * " +
//            "FROM Hotels ", nativeQuery = true)
//    List<Hotels> findHotelsInLocationWithMostHotels();
	
	@Query(value = "SELECT * FROM Hotels h  order by h.hotel_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Hotels> findPageAdmin(Integer page, Integer number);
	
	@Query("SELECT p FROM Hotels p where p.name = ?1")
	Hotels findByHotelName(String name);
}

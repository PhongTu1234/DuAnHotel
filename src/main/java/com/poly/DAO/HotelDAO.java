package com.poly.DAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Hotels;

public interface HotelDAO extends JpaRepository<Hotels, Integer> {
	@Query("SELECT p FROM Hotels p WHERE p.Place.id= ?1")
	List<Hotels> findByPlaceId(Integer id);

	@Query("SELECT h FROM Hotels h WHERE h.Level between ?1 and ?2 AND h.Place.id=?3")
	List<Hotels> findHotelByHotelLevelstarttoend(Integer start, Integer end, Integer placeId);
	
	@Query("SELECT h FROM Hotels h WHERE h.Level = ?1 ")
	List<Hotels> findHotelByHotelLevel(Integer level);
	
	Page<Hotels> findAll(Pageable page);
	
	@Query("Select h from Hotels h")
	List<Hotels> countHotel();

	@Query(value = "select * from Hotels where Hotels.place_id = ?1", nativeQuery = true)
	Page<Hotels> findHotelByPlaceid(Integer id, Pageable page );
	
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
	
//	@Query(value = "SELECT * FROM Hotels h  order by h.hotel_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
//	List<Hotels> findPageAdmin(Integer page, Integer number);
	
	@Query("SELECT p FROM Hotels p where p.name = ?1")
	Hotels findByHotelName(String name);

	@Query(value =
		    "SELECT DISTINCT h.* " +
		    "FROM Hotels h " +
		    "JOIN Rooms r ON h.hotel_id = r.hotel_id " +
		    "JOIN service_rooms sr ON r.room_id = sr.room_id " +
		    "JOIN Services s ON sr.service_id = s.service_id " +
		    "JOIN RoomTypes rt ON r.room_type_id = rt.room_type_id " +
		    "WHERE (?1 IS NULL OR s.service_id IN (?1)) " +
		    "AND (?2 IS NULL OR rt.room_type_id IN (?2)) " +
		    "AND (?3 IS NULL OR r.price >= ?3) " +
		    "AND (?4 IS NULL OR r.price <= ?4)",
		    countQuery = "SELECT COUNT(DISTINCT h.hotel_id) " +
		                 "FROM Hotels h " +
		                 "JOIN Rooms r ON h.hotel_id = r.hotel_id " +
		                 "JOIN service_rooms sr ON r.room_id = sr.room_id " +
		                 "JOIN Services s ON sr.service_id = s.service_id " +
		                 "JOIN RoomTypes rt ON r.room_type_id = rt.room_type_id " +
		                 "WHERE (?1 IS NULL OR s.service_id IN (?1)) " +
		                 "AND (?2 IS NULL OR rt.room_type_id IN (?2)) " +
		                 "AND (?3 IS NULL OR r.price >= ?3) " +
		                 "AND (?4 IS NULL OR r.price <= ?4)", nativeQuery = true)
		Page<Hotels> findHotelsBySearchCriteria(@Param("1") List<Integer> serviceIds,
		                                       @Param("2") List<Integer> roomTypeIds,
		                                       @Param("3") BigDecimal minPrice,
		                                       @Param("4") BigDecimal maxPrice,
		                                       Pageable pageable);




//	@Query("SELECT DISTINCT h FROM Hotels h " +
//            "JOIN h.rooms r " +
//            "JOIN r.service_Room sr " +
//            "WHERE " +
//            "(:serviceIds IS NULL OR sr.services.id IN :serviceIds) AND " +
//            "(:roomTypeIds IS NULL OR r.roomTypes.id IN :roomTypeIds) AND " +
//            "(:minPrice IS NULL OR r.price >= :minPrice) AND " +
//            "(:maxPrice IS NULL OR r.price <= :maxPrice)")
//    Page<Hotels> findHotelsBySearchCriteria(
//            @Param("serviceIds") List<Integer> serviceIds,
//            @Param("roomTypeIds") List<Integer> roomTypeIds,
//            @Param("minPrice") Double minPrice,
//            @Param("maxPrice") Double maxPrice,
//            Pageable page
//    );
}

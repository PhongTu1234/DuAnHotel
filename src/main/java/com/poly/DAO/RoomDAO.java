package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Hotels;
import com.poly.entity.Rooms;
import com.poly.entity.Services;

public interface RoomDAO extends JpaRepository<Rooms, Integer> {
	@Query("SELECT p FROM Rooms p WHERE p.RoomTypes.id=?1")
	List<Rooms> findByRoomTypesId(String htid);
	
	Page<Rooms> findAll(org.springframework.data.domain.Pageable page);

	@Query(value = "SELECT count(p.id) FROM Products p", nativeQuery = true)
	Integer countAllProduct();

	@Query("SELECT p FROM Rooms p WHERE p.Hotels.id=?1")
	List<Rooms> findByHotelId(Integer hid);
	
	@Query("SELECT p FROM Rooms p WHERE p.Hotels.id=?1")
	Page<Rooms> adfindByHotelId(Integer hid, Pageable page);

	@Query("SELECT p FROM Rooms p WHERE p.id between 1513 and 1520")
	List<Rooms> findByRoom1to8();
	
	@Query(value = "SELECT TOP 8 * FROM Rooms r ORDER BY r.rating DESC ", nativeQuery = true)
    List<Rooms> findTop8ByOrderByRatingDesc();
	
	@Query("SELECT p FROM Rooms p where p.name = ?1")
	Rooms findByRoomName(String name);
	
	@Query(value = "SELECT TOP 8 r.* FROM Booking_room b " +
            "JOIN Rooms r ON b.room_id = r.room_id " +
            "JOIN Bookings bk ON b.booking_id = bk.booking_id " +
            "WHERE bk.payment_status = 1 " +
            "GROUP BY r.room_id, r.roomname, r.rating, r.price, r.soluongphong, r.soluongchocheckin, r.soluongtrong, r.soluongdangthue, r.description, r.room_type_id, r.hotel_id " +
            "ORDER BY COUNT(b.bookingroom_id) DESC", nativeQuery = true)
    List<Rooms> findTop8RoomsByTotalBookingsAndPaymentStatus();
	
	@Query("SELECT h FROM Hotels h "
//			+ " LEFT JOIN Rooms r ON r.hotel_id = h.hotel_id"
//			+ " LEFT JOIN RoomTypes rt ON r.room_type_id = rt.room_type_id",
	)
	Page<Hotels> findHotelAndRoomType(Pageable page);  
	
}

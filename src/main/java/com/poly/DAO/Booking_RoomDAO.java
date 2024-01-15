package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Booking_Room;
import com.poly.entity.Bookings;
import com.poly.entity.Payment;
import com.poly.entity.Rooms;

public interface Booking_RoomDAO extends JpaRepository<Booking_Room, Integer> {
	@Query(value = "SELECT * FROM Booking_room h  order by h.bookingroom_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Booking_Room> findPageAdmin(Integer page, Integer number);

    @Query("SELECT br " +
            "FROM Booking_Room br " +
            "WHERE br.Bookings.Users.cmt = ?1 and br.Bookings.Status = 0")
     List<Booking_Room> getBookingDetailsForUser(String userCmt);
     
     Page<Booking_Room> findAll(Pageable page);
     
     @Query("SELECT p FROM Booking_Room p WHERE p.Rooms.Hotels.id=?1")
 	Page<Booking_Room> adfindByHotelId(Integer hid, Pageable page);
 	
 	@Query("SELECT b FROM Booking_Room b WHERE b.Bookings.id = ?1")
	List<Booking_Room> findByIds(Integer id);
}

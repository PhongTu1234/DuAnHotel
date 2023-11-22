package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Booking_Room;

public interface Booking_RoomDAO extends JpaRepository<Booking_Room, Integer> {
    @Query("SELECT br " +
            "FROM Booking_Room br " +
            "WHERE br.Bookings.Users.cmt = ?1 and br.Bookings.Status = 0")
     List<Booking_Room> getBookingDetailsForUser(String userCmt);
}
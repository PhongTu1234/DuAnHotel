package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Bookings;

public interface BookingsDAO extends JpaRepository<Bookings, Integer> {

}

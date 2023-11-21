package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.ServiceRooms;

public interface ServiceRoomsDAO extends JpaRepository<ServiceRooms, Integer> {

}

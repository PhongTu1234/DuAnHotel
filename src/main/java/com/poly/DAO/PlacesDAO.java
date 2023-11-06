package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Places;

public interface PlacesDAO extends JpaRepository<Places, Integer> {

    @Query(value = "SELECT TOP 1 p.place_id, p.place_name " +
            "FROM Place p " +
            "JOIN Hotels h ON p.place_id = h.place_id " +
            "GROUP BY p.place_id, p.place_name " +
            "ORDER BY COUNT(h.hotel_id) DESC", nativeQuery = true)
    Places findPlaceWithMostHotels();
    
    @Query(value = "SELECT p.place_id, p.place_name  " +
            "FROM Place p " +
            "JOIN Hotels h ON p.place_id = h.place_id " +
            "GROUP BY p.place_id, p.place_name " +
            "ORDER BY COUNT(h.hotel_id) DESC " +
            "OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
    Places findPlaceWithMostHotelsTop2();
    
    @Query(value = "SELECT p.place_id, p.place_name " +
            "FROM Place p " +
            "JOIN Hotels h ON p.place_id = h.place_id " +
            "GROUP BY p.place_id, p.place_name " +
            "ORDER BY COUNT(h.hotel_id) DESC " +
            "OFFSET 2 ROWS FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
    Places findPlaceWithMostHotelsTop3();
    
    @Query(value = "SELECT p.place_id, p.place_name " +
            "FROM Place p " +
            "JOIN Hotels h ON p.place_id = h.place_id " +
            "GROUP BY p.place_id, p.place_name " +
            "ORDER BY COUNT(h.hotel_id) DESC " +
            "OFFSET 3 ROWS FETCH NEXT 1 ROWS ONLY", nativeQuery = true)
    Places findPlaceWithMostHotelsTop4();
}

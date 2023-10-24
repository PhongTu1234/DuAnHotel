package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Places;

public interface PlacesDAO extends JpaRepository<Places, Integer> {

}

package com.poly.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Services;

public interface ServiceDAO extends JpaRepository<Services, Integer> {

}

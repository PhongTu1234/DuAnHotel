package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Rooms;
import com.poly.entity.Services;

public interface ServiceDAO extends JpaRepository<Services, Integer> {
}

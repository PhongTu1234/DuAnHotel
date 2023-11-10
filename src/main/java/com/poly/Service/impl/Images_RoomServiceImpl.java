package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.Images_RoomDAO;
import com.poly.Service.Images_RoomService;
import com.poly.entity.Images_Room;

@Service
public class Images_RoomServiceImpl implements Images_RoomService {

	@Autowired
	Images_RoomDAO htdao;

	@Override
	public List<Images_Room> findAll() {
		return htdao.findAll();
	}

	@Override
	public Images_Room findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public Images_Room create(Images_Room Images_Room) {
		return htdao.save(Images_Room);
	}

	@Override
	public Images_Room update(Images_Room Images_Room) {
		return htdao.save(Images_Room);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

}

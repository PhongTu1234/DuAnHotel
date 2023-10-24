package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.Service_RoomDAO;
import com.poly.Service.Service_RoomService;
import com.poly.entity.Service_Rooms;

@Service
public class Service_RoomServiceImpl implements Service_RoomService {

	@Autowired
	Service_RoomDAO htdao;

	@Override
	public List<Service_Rooms> findAll() {
		return htdao.findAll();
	}

	@Override
	public Service_Rooms findById(Integer id) {
		return htdao.findById(id).get();
	}

	public Service_Rooms create(Service_Rooms Service_Rooms) {
		return htdao.save(Service_Rooms);
	}

	@Override
	public Service_Rooms update(Service_Rooms Service_Rooms) {
		return htdao.save(Service_Rooms);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

}

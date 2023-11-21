package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.ServiceRoomsDAO;
import com.poly.Service.ServiceRoomsService;
import com.poly.entity.ServiceRooms;

@Service
public class ServiceRoomsServiceImpl implements ServiceRoomsService {

	@Autowired
	ServiceRoomsDAO htdao;

	@Override
	public List<ServiceRooms> findAll() {
		return htdao.findAll();
	}

	@Override
	public ServiceRooms findById(Integer id) {
		return htdao.findById(id).get();
	}

	@Override
	public ServiceRooms create(ServiceRooms Service_Rooms) {
		return htdao.save(Service_Rooms);
	}

	@Override
	public ServiceRooms update(ServiceRooms Service_Rooms) {
		return htdao.save(Service_Rooms);
	}

	@Override
	public void delete(Integer id) {
		htdao.deleteById(id);
	}

}

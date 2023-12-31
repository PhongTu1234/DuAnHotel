package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.ServiceRoomsDAO;
import com.poly.Service.ServiceRoomsService;
import com.poly.entity.ServiceRooms;

@Service
public class ServiceRoomsServiceImpl implements ServiceRoomsService {

	@Autowired
	ServiceRoomsDAO htdao;

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

	@Override
	public List<ServiceRooms> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return htdao.findPageAdmin(page, number);
	}

	@Override
	public Page<ServiceRooms> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return htdao.findAll(page);
	}

}

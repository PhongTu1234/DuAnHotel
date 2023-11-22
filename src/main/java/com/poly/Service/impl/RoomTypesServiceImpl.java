package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.RoomTypesDAO;
import com.poly.Service.RoomTypesService;
import com.poly.entity.RoomTypes;

@Service
public class RoomTypesServiceImpl implements RoomTypesService {

	@Autowired
	RoomTypesDAO rtdao;

	@Override
	public List<RoomTypes> findAll() {
		return rtdao.findAll();
	}

	@Override
	public RoomTypes findById(Integer id) {
		return rtdao.findById(id).get();
	}

	@Override
	public RoomTypes create(RoomTypes HotelTypes) {
		return rtdao.save(HotelTypes);
	}

	@Override
	public RoomTypes update(RoomTypes HotelTypes) {
		return rtdao.save(HotelTypes);
	}

	@Override
	public void delete(Integer id) {
		rtdao.deleteById(id);
	}

	@Override
	public List<RoomTypes> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return rtdao.findPageAdmin(page, number);
	}

	@Override
	public RoomTypes findByRoomtypeName(String name) {
		// TODO Auto-generated method stub
		return rtdao.findByPlaceName(name);
	}


}

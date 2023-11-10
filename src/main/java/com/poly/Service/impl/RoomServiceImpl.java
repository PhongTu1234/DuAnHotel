package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.RoomDAO;
import com.poly.Service.RoomService;
import com.poly.entity.Rooms;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomDAO rdao;

	@Override
	public List<Rooms> findAll() {
		return rdao.findAll();
	}

	@Override
	public Rooms findById(Integer id) {
		return rdao.findById(id).get();
	}

	@Override
	public List<Rooms> findByRoomTypesId(String cid) {
		return rdao.findByRoomTypesId(cid);
	}

	@Override
	public Rooms create(Rooms Rooms) {
		return rdao.save(Rooms);
	}

	@Override
	public Rooms update(Rooms Rooms) {
		return rdao.save(Rooms);
	}

	@Override
	public void delete(Integer id) {
		rdao.deleteById(id);
	}

	@Override
	public List<Rooms> findByHotelId(Integer hid) {
		return rdao.findByHotelId(hid);
	}

	@Override
	public List<Rooms> findByRoom1to8() {
		return rdao.findByRoom1to8();
	}

}

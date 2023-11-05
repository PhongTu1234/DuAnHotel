package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DAO.RoleDAO;
import com.poly.entity.Role;
import com.poly.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO dao;

	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Role findById(Integer id) {
		return dao.findById(id).get();
	}

	public Role create(Role HotelTypes) {
		return dao.save(HotelTypes);
	}

	@Override
	public Role update(Role HotelTypes) {
		return dao.save(HotelTypes);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}

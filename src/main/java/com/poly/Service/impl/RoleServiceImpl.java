package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.RoleDAO;
import com.poly.Service.RoleService;
import com.poly.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Role findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public Role create(Role HotelTypes) {
		return dao.save(HotelTypes);
	}

	@Override
	public Role update(Role HotelTypes) {
		return dao.save(HotelTypes);
	}

	@Override
	public void delete(String id) {
		dao.deleteById(id);
	}

	@Override
	public List<Role> findPageAdmin(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return dao.findPageAdmin(page, number);
	}

	@Override
	public Page<Role> findAlla(Pageable page) {
		// TODO Auto-generated method stub
		return dao.findAll(page);
	}

}

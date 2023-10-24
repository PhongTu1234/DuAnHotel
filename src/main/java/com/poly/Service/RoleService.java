package com.poly.Service;

import java.util.List;

import com.poly.entity.HotelTypes;
import com.poly.entity.Role;

public interface RoleService {
	public List<Role> findAll();
	
	Role findById(Integer id);

	Role create(Role hoteltypes);

	Role update(Role hoteltypes);

	void delete(Integer id);
}

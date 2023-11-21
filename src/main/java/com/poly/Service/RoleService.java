package com.poly.Service;

import java.util.List;

import com.poly.entity.Role;

public interface RoleService {
	public List<Role> findAll();

	Role findById(String id);

	Role create(Role hoteltypes);

	Role update(Role hoteltypes);

	void delete(String id);
}

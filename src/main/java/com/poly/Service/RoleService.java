package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Places;
import com.poly.entity.Role;

public interface RoleService {
	public List<Role> findAll();

	Role findById(String id);

	Role create(Role hoteltypes);

	Role update(Role hoteltypes);

	void delete(String id);
	
	List<Role> findPageAdmin(Integer page, Integer number);

	Page<Role> findAlla(Pageable page);
}

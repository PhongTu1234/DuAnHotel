package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DAO.AuthorityDAO;
import com.poly.DAO.UserDAO;
import com.poly.Service.AuthorityService;
import com.poly.entity.Authority;
import com.poly.entity.Hotels;
import com.poly.entity.Users;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDAO dao;
	@Autowired
	UserDAO acdao;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Users> accounts = acdao.getAdministrators();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public Page<Authority> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return dao.findAll(page);
	}

	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}

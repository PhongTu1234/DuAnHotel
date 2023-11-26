package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poly.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAuthoritiesOfAdministrators();

	public List<Authority> findAll();

	public Authority create(Authority auth);

	public void delete(Integer id);
}

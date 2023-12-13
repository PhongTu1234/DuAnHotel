package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAuthoritiesOfAdministrators();

	Page<Authority> findAll(Pageable page);

	public Authority create(Authority auth);

	public void delete(Integer id);
	
	List<Authority> findRoleByCmt(String cmt);
	
	public void deleteAuthoritiesByUserCmt(String cmt);
}

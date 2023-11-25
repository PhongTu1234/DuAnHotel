package com.poly.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.poly.DAO.UserDAO;
import com.poly.Service.UserService;
import com.poly.entity.Users;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO adao;

	@Autowired
	PasswordEncoder pe;

	@Override
	public Users findById(String cmt) {
		return adao.findById(cmt).get();
	}

	@Override
	public List<Users> findAll() {
		return adao.findAll();
	}
	
	@Override
	public Page<Users> findAlla(Pageable page) {
		return adao.findAll(page);
	}

	@Override
	public List<Users> getAdministrators() {
		return adao.getAdministrators();
	}

	@Override
	public Users create(Users account) {
		return adao.save(account);
	}

	@Override
	public Users update(Users account) {
		return adao.save(account);
	}

	@Override
	public void delete(String cmt) {
		adao.deleteById(cmt);
	}

	@Override
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		// String fullname = oauth2.getPrincipal().getAttribute("name");
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());

		UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("CUST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public void updateToken(String token, String email) throws Exception {
		Users entity = adao.findByEmail(email);
		if (entity != null) {
			entity.setToken(token);
			adao.save(entity);
		} else {
			throw new Exception("Cannot find any account with email: " + email);
		}
	}

	@Override
	public Users getByToken(String token) {
		return adao.findByToken(token);
	}

	@Override
	public void updatePassword(Users entity, String newPassword) {
		entity.setPassword(newPassword);
		entity.setToken("token");
		adao.save(entity);
	}

	@Override
	public void changePassword(Users entity, String newPassword) {
		entity.setPassword(newPassword);
		adao.save(entity);
	}

	@Override
	public void updatePass(String password, String id) {
		adao.updatePasswordAndChangedPassById(password, id);
	}

	@Override
	public List<Users> getAdministrators(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findPage(Integer page, Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findByUserName(String name) {
		// TODO Auto-generated method stub
		return adao.findByUserName(name);
	}
}

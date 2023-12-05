package com.poly.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.poly.entity.Users;

public interface UserService {
	Users findById(String cmt);

	Page<Users> findAll(Pageable page);
	
	List<Users> findAll();

	List<Users> getAdministrators();

	Users create(Users account);

	Users update(Users account);

	void delete(String cmt);

	void loginFromOAuth2(OAuth2AuthenticationToken oauth2);

	void updateToken(String token, String email) throws Exception;

	Users getByToken(String token);

	void updatePassword(Users entity, String newPassword);

	void changePassword(Users entity, String newPassword);
	
	void updatePass(String id, String password);
	
	List<Users> getAdministrators(String role);
	
	List<Users> findPage(Integer page, Integer number);
	
	Users findByUserName(String name);

}

package com.poly.Service;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.poly.entity.Users;

public interface UserService {
	Users findById(String username);

	List<Users> findAll();

	List<Users> getAdministrators();

	Users create(Users account);

	Users update(Users account);

	void delete(String username);

	void loginFromOAuth2(OAuth2AuthenticationToken oauth2);

	void updateToken(String token, String email) throws Exception;

	Users getByToken(String token);

	void updatePassword(Users entity, String newPassword);

	void changePassword(Users entity, String newPassword);
}

package com.poly.Service;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.poly.entity.Users;

@Service
public interface UserService{

	Users getUserByCMT(String cmt);
	
	Users findById(String username);

	List<Users> findAll();

	//List<Users> getAdministrators();

	Users create(Users Users);

	Users update(Users Users);

	void delete(String username);

	void loginFromOAuth2(OAuth2AuthenticationToken oauth2);

	void updateToken(String token, String email) throws Exception;

	//Users getByToken(String token);

	void updatePassword(Users entity, String newPassword);

	void changePassword(Users entity, String newPassword);
}

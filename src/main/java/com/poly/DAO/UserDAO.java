package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Users;

public interface UserDAO extends JpaRepository<Users, String> {

	@Query("SELECT a FROM Users a WHERE a.cmt =?1")
	Users getAccount(String cmt);
	
//	@Query("SELECT DISTINCT ar.account FROM Users ar WHERE ar.role.id IN('DIRE','STAF')")
//	List<Users> getAdministrators();

//	@Query("SELECT a FROM Account a WHERE a.username =?1 and a.password=?2")
//	Account getAccount(String username, String password);

	// Phuc vu viec gui mail
	@Query("SELECT a FROM Users a WHERE a.email=?1")
	public Users findByEmail(String email);

//	@Query("SELECT a FROM Users a WHERE a.phone_number=?1")
//	public Users findByToken(String token);

//	@Query(value = "SELECT count(a.username) FROM Accounts a", nativeQuery = true)
//	Integer countAllAccount();

}

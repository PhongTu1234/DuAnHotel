package com.poly.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Users;

public interface UserDAO extends JpaRepository<Users, String> {
	@Query("SELECT DISTINCT ar.Users FROM Authority ar WHERE ar.Role.id IN('DIRE','STAF')")
	List<Users> getAdministrators();


	@Query("SELECT u FROM Users u WHERE u.username =?1 and u.password=?2")
	Users getAccount(String username, String password);

	// Phuc vu viec gui mail
	@Query("SELECT u FROM Users u WHERE u.email=?1")
	public Users findByEmail(String email);

	@Query("SELECT u FROM Users u WHERE u.token=?1")
	public Users findByToken(String token);
	
	@Modifying
    @Query("UPDATE Users u SET u.password = :newPassword, u.Changedpass = true WHERE u.cmt = :userId")
    void updatePasswordAndChangedPassById(@Param("userId") String userId, @Param("newPassword") String newPassword);
	

}

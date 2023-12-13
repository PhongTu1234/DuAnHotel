package com.poly.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.poly.entity.Authority;
import com.poly.entity.Users;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.Users IN ?1")
	List<Authority> authoritiesOf(List<Users> accounts);
	
	@Query("SELECT a FROM Authority a WHERE a.Users.cmt = ?1")
	List<Authority> findRoleByCmt(String cmt);

	@Transactional
    @Modifying
    @Query("DELETE FROM Authority a WHERE a.Users.cmt = :cmt")
    void deleteByUserCmt(String cmt);

}
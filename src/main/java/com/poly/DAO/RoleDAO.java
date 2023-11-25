package com.poly.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Places;
import com.poly.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {
	@Query(value = "SELECT * FROM Roles h  order by h.role_id OFFSET ?1 ROWS FETCH NEXT ?2 ROWS only", nativeQuery = true)
	List<Role> findPageAdmin(Integer page, Integer number);
	
	Page<Role> findAll(org.springframework.data.domain.Pageable page);

}

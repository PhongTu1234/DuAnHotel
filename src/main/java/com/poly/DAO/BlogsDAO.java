package com.poly.DAO;


import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Blogs;

public interface BlogsDAO extends JpaRepository<Blogs, Integer> {

}

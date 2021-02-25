package com.datos.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datos.demo.model.User;

public interface UserRepository extends JpaRepository<User , Long>{

	
	@Query("Select u From User u Where u.uname=?1")
	public User findByUname(String uname);
}

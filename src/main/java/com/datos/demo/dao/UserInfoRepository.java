package com.datos.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datos.demo.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo , Long> {

	@Query("Select u From UserInfo u Where u.uname=?1")
	public UserInfo findByUname(String uname);

}

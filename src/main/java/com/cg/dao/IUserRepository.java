package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	// find user by userId
	public User findUserByUserId(String userId);
}

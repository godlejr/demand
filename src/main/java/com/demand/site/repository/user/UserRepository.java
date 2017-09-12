package com.demand.site.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmailAndPassword(String email, String password) throws Exception;
	
}

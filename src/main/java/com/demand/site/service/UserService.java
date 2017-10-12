package com.demand.site.service;

import com.demand.site.common.entity.User;

public interface UserService {

	User getUserByEmailAndPassword(String email, String password) throws Exception;

	boolean isEmailExist(String email) throws Exception;

	void saveUserWithCheck(User user, int checked) throws Exception;

	User getUserById(long id) throws Exception;

}

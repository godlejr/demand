package com.demand.site.service;

import com.demand.site.common.entity.User;

public interface UserService {

	User getUserByEmailAndPassword(String email, String password) throws Exception;

}

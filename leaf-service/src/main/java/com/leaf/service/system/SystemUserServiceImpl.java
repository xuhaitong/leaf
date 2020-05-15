package com.leaf.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaf.service.system.dao.SystemUserDao;
import com.leaf.service.system.domain.SystemUser;

@Service("userService")
public class SystemUserServiceImpl implements SystemUserService{

	@Autowired
	private SystemUserDao userDao;
	
	@Override
	public SystemUser getByUsername(String username) {
		SystemUser user = new SystemUser();
		user.setUserName(username);
//		return user;
		return userDao.selectOne(user);
	}
	
}

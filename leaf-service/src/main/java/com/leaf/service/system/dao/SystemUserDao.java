package com.leaf.service.system.dao;

import com.leaf.mybatis.support.BaseMapper;
import com.leaf.mybatis.support.annotation.MyBatisDao;
import com.leaf.service.system.domain.SystemUser;

//@Repository
//@Mapper
@MyBatisDao
// public interface SystemUserDao<T> extends BaseMapper<T> {
public interface SystemUserDao extends BaseMapper<SystemUser> {
	SystemUser selectOne(SystemUser user);

}

package com.leaf.boot.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.leaf.plugin.security.BaseUserDetails;
import com.leaf.service.system.SystemUserService;
import com.leaf.service.system.domain.SystemUser;

@Component
public class BaseUserDetailsService implements UserDetailsService{

	
	@Autowired
	private SystemUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		SystemUser user = userService.getByUsername(username);
//		 String code, String name, String orgId, String email, long notifyCount, String avatar, Collection<? extends GrantedAuthority> authorities, boolean enabled, boolean accountNonLocked, Date lastPasswordResetDate, boolean isAdmin, String userMrgType
		return new BaseUserDetails(
				user.getId(),
				user.getUserName(),
				user.getUserPwd(),
				user.getUserCode(),
				user.getUserFullName(),
				user.getFkOrgId(),
				user.getUserEmail(),
				0,
				null,
				null, true, true, null, false, null);
		//return new BaseUserDetails(username, username, "password", username, username, username, username, 0, username, null, true, true, null, false, username);
	}

}

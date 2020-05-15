package com.leaf.boot.security.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.leaf.plugin.security.BaseAuthenticationProvider;

public class CustomAuthenticationProvider extends BaseAuthenticationProvider{

	/**
	 * 自定义身份校验
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
}

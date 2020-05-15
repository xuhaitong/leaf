package com.leaf.boot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.leaf.boot.security.provider.CustomAuthenticationProvider;
import com.leaf.boot.security.service.BaseUserDetailsService;
import com.leaf.plugin.security.BaseAuthenticationProvider;
import com.leaf.plugin.security.config.BaseSecurityConfig;


/**
 * @author Leaf Xu
 */
//@Configuration
//@EnableWebSecurity
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(200)
public class BootSecurityConfig extends BaseSecurityConfig{

	@Override
	public BaseAuthenticationProvider getAuthenticationProviderInstance() {
		return new CustomAuthenticationProvider();
	}
	
	@Override
	protected UserDetailsService userDetailsService() {
		return new BaseUserDetailsService();
	}
	
	
}
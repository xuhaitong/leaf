package com.leaf.plugin.security.config;

import javax.servlet.Filter;

/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.leaf.plugin.security.AuthEntryPoint;
import com.leaf.plugin.security.AuthTokenFilter;
import com.leaf.plugin.security.BaseAuthenticationProvider;
import com.leaf.plugin.security.TokenManager;


/**
 * @author Leaf Xu
 */
@Configuration
@EnableWebSecurity
public class BaseSecurityConfig extends WebSecurityConfigurerAdapter{

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
				
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
					.antMatchers(
							HttpMethod.GET, 
							"/",
							"/login/**",
							"/login*",
							"/api/common/**",
							"/**/free/**")
					.permitAll()
					.antMatchers(
							"/login/**",
							"/login*",
							"/api/common/**",
							"/**/free/**")
					.permitAll()
				.and()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling()
				.authenticationEntryPoint(unAuthorizeHandler());
//					.and();
					
	}
	
	@Bean
	public AuthenticationEntryPoint unAuthorizeHandler() {
		// TODO Auto-generated method stub
		return new AuthEntryPoint();
	}

	@Bean
	public Filter authTokenFilter() {
		return new AuthTokenFilter();
	}
	// @formatter:off
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());//.userDetailsService(userDetailsService()).;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		BaseAuthenticationProvider provider =  getAuthenticationProviderInstance();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
//	@Bean
	public BaseAuthenticationProvider getAuthenticationProviderInstance() {
		return new BaseAuthenticationProvider();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public TokenManager tokenManager() {
		return new TokenManager();
	}
	
}
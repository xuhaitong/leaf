package com.leaf.plugin.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具
 * @author siufung
 *
 */
public class SecurityUtil {
	
	public static Authentication getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static void logout(){
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
	}
	
}

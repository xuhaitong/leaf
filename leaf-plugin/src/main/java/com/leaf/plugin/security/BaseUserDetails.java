package com.leaf.plugin.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BaseUserDetails implements UserDetails {

	/**
	 * 
	 */
	@Getter
	@JsonIgnore
	private final String id;	//主键
    private final String username;	//用户名
    private final String password;	//密码
	@Getter
    private final String code;	//编码
	@Getter
    private final String name;	//名称
	@Getter
    private final String orgId; //组织id
	@Getter
    private final String email;	//邮箱
	@Getter
    private final long notifyCount;	//消息总数
	@Getter
    private final String avatar;	//头像
    private final Collection<? extends GrantedAuthority> authorities;	//权限
    private final boolean enabled;	//是否登录
    
    private final boolean accountNonLocked;	//
    @Getter
    private final Date lastPasswordResetDate;	//最后更新时间
    @Getter
    private final boolean isAdmin;                     // 是否是管理员用户
    @Getter
    private final String userMrgType; //用户类型
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}

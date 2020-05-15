package com.leaf.service.system.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * SystemUser
 * @author qd_chenzhaofeng@163.com
 * @version 2018-05-30 10:10 v1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SystemUser { 
	 
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@NotBlank(message="用户名称不能为空!")
	@Length(min=1, max=16,message="用户名称长度必须介于1~16之间")
	private String userName;//用户名称 
	
	@NotBlank(message="用户密码不能为空!")
	@Length(min=1, max=255,message="用户密码长度必须介于1~255之间")
	private String userPwd;//用户密码 
	
	@NotBlank(message="用户密码明文不能为空!")
	@Length(min=1, max=255,message="用户密码明文长度必须介于1~255之间")
	private String userPlainPwd;//用户密码明文 
	
	@NotBlank(message="用户工号，可自动生成不能为空!")
	@Length(min=1, max=16,message="用户工号，可自动生成长度必须介于1~16之间")
	private String userCode;//用户工号，可自动生成 
	
	@Length(max=255,message="用户全称长度不能超过255位字符")
	private String userFullName;//用户全称 
	
	private String fkOrgId;//用户组织ID 
	
	@Length(max=16,message="用户组织编码长度不能超过16位字符")
	private String orgCode;//用户组织编码 
	
	@Length(max=16,message="用户组织编码长度不能超过16位字符")
	private String orgName;//用户组织编码 
	
	private String fkStationId;//用户岗位ID 
	
	private String userPhone;//用户手机 
	
	@Length(max=255,message="微信号长度不能超过255位字符")
	private String userWxOpenid;//微信号 
	
	@Length(max=255,message="用户邮箱长度不能超过255位字符")
	private String userEmail;//用户邮箱 
	
	@NotBlank(message="用户类型（0非管理员 1系统管理员  2二级管理员）不能为空!")
	@Length(min=1, max=1,message="用户类型（0非管理员 1系统管理员  2二级管理员）长度必须介于1~1之间")
	private String userMrgType;//用户类型（1系统管理员  2运营管理员 3店铺主账户 4店铺子账户 5消费者） 
	
	@NotBlank(message="用户性别不能为空!")
	@Length(min=1, max=1,message="用户性别长度必须介于1~1之间")
	private String userSex;//用户性别 
	
	@Length(max=255,message="用户头像长度不能超过255位字符")
	private String userAvatar;//用户头像 
	
	@Length(max=1,message="是否限定登陆，0不限定，1限定长度不能超过1位字符")
	private String userIsastrict;//是否限定登陆，0不限定，1限定 
	
	@Length(max=255,message="限定登陆时间长度不能超过255位字符")
	private String userAstrictLogintime;//限定登陆时间 
	
	@NotBlank(message="是否允许手机登陆，0允许1不允许不能为空!")
	@Length(min=1, max=1,message="是否允许手机登陆，0允许1不允许长度必须介于1~1之间")
	private String userIswap;//是否允许手机登陆，0允许1不允许 
	
	@Length(max=255,message="最后登陆IP长度不能超过255位字符")
	private String userLoginLastip;//最后登陆IP 
	
	private Date userLoginLastdate;//最后登陆时间 
	
	private Integer userSort;//排序 
	
	@Length(max=255,message="拼音长度不能超过255位字符")
	private String pinyin;//拼音 
	
	@Length(max=255,message="数据域长度不能超过255位字符")
	private String dataScope;//数据域 
	
	@Length(max=600,message="区域权限长度不能超过255位字符")
	private String areaScope;//区域权限
	
//	private List<SystemPermi> permissions = Lists.newArrayList(); //权限标识
	
	private String verCode;
	
	private String noteid;
	
	private String userPwdConfirm;
	
	private String userFullNameLike;
	private String userNameLike;
	private String userPhoneLike;
}


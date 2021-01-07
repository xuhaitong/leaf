package com.leaf.freemarker.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: Leaf Xu
 * @description:
 * @Date: 2021-01-05 16:49
 **/
public class GenerateDBConfig {
	/* 数据库名称 */
	public static final String moduleName = "resource_manage";
	/* 数据库连接地址 */
	public static final String url = "jdbc:mysql://localhost:3306/" + moduleName + "?allowMultiQueries=true&useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
	/* 用户名 */
	public static final String user = "xuht";
	/* 密码 */
	public static final String password = "xuht";
	/* 数据库驱动 */
	public static final String driverName = "com.mysql.cj.jdbc.Driver";
	/*是否生整个库的所有表*/
	public static final boolean allTable = false;
	/* 要生成的表模糊匹配(假如表设计的没有模块概念该参数就设置成一个没法模糊匹配表的字符串) */
	public static final String likeTable = "465sdagagdguhauihfjslfasjofads";
	/* 假如表设计的没有功能模块标志,则把要生的表放入该数组 */
	public static final String[] arrayTable = {"system_org"};

	public static Connection connect;



	//初始化
	public static void init() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		connect = DriverManager.getConnection(url, user, password);
	}
}

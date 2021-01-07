package com.leaf.freemarker.tool;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: Leaf Xu
 * @description:
 * @Date: 2020-11-02 14:31
 **/
public class DataBaseConnect {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(Driver.class.getName());

		DriverManager.getConnection("localhost");


	}
}

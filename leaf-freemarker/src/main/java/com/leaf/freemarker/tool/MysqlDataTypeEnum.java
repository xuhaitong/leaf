package com.leaf.freemarker.tool;

import com.mysql.cj.exceptions.StatementIsClosedException;

import java.io.File;
import java.util.Arrays;

/**
 * @author: Leaf Xu
 * @description: MYSQL JAVA 数据类型对应枚举
 * @Dte: 2021-01-21 10:03
 **/
public enum MysqlDataTypeEnum {

	/**/
	BIT("BIT","byte[]"),
	TINYINT("TINYINT","Integer"),
	SMALLINT("SMALLINT","Integer"),
	MEDIUMINT("MEDIUMINT","Integer"),
	INTEGER("INTEGER","Long"),
	BIGINT("BIGINT","java.math.BigInteger"),
	INT("INT","Long"),
	FLOAT("FLOAT","Float"),
	DOUBLE("DOUBLE","Double"),
	DECIMAL("DECIMAL","java.math.BigDecimal"),
	DATE("DATE","java.util.Date"),
	DATETIME("DATETIME","java.util.Date"),
	TIMESTAMP("TIMESTAMP","java.util.Date"),
	TIME("TIME","java.util.Date"),
	YEAR("YEAR","java.util.Date"),
	CHAR("CHAR","String"),
	VARCHAR("VARCHAR","String"),
	BINARY("BINARY","byte[]"),
	VARBINARY("VARBINARY","byte[]"),

	TEXT("TEXT","String"),
	TINYTEXT("TINYTEXT","String"),
	MEDIUMTEXT("MEDIUMTEXT","String"),
	LONGTEXT("LONGTEXT","String"),
	BOOL("BOOL","Boolean"),
    BOOLEAN(" BOOLEAN","Boolean"),
	ENUM("ENUM","String"),
	BLOB("BLOB","byte[]"),
	LONGBLOB("LONGBLOB","byte[]"),
	MEDIUMBLOB("MEDIUMBLOB","byte[]"),
	TINYBLOB("TINYBLOB","byte[]"),
	ET("ET","String"),
	UNKNOWN("UNKNOWN","Object");

	MysqlDataTypeEnum(String mysqlDataType, String javaType) {
		this.mysqlDataType = mysqlDataType;
		this.javaType = javaType;
	}

	private String mysqlDataType;
	private String javaType;

	public String getMysqlDataType() {
		return mysqlDataType;
	}


	public String getJavaType() {
		return javaType;
	}


	public static String getJavaType(String fieldType){
		String res = null;
		MysqlDataTypeEnum typeEnum =
				Arrays.stream(MysqlDataTypeEnum.values())
						.filter(item-> fieldType.toUpperCase().contains(item.getMysqlDataType()))
						//.filter(item->fieldType.indexOf(item.getMysqlDataType())==0)
						.findFirst()
						.orElse(UNKNOWN);
		return typeEnum.getJavaType();
//		return res;
	}
}

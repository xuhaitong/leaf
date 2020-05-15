package com.leaf.boot.mybatis.support.plugins.pagination;

import org.apache.commons.lang3.StringUtils;

import com.leaf.mybatis.support.plugins.dialects.DB2Dialect;
import com.leaf.mybatis.support.plugins.dialects.H2Dialect;
import com.leaf.mybatis.support.plugins.dialects.HSQLDialect;
import com.leaf.mybatis.support.plugins.dialects.MySqlDialect;
import com.leaf.mybatis.support.plugins.dialects.OracleDialect;
import com.leaf.mybatis.support.plugins.dialects.PostgreDialect;
import com.leaf.mybatis.support.plugins.dialects.SQLServer2005Dialect;
import com.leaf.mybatis.support.plugins.dialects.SQLServerDialect;
import com.leaf.mybatis.support.plugins.dialects.SQLiteDialect;

public class DialectFactory {
	public static String buildPaginationSql(Pagination<?> page, String buildSql, String dbType) throws Exception {
		return getDialect(dbType, null).buildPaginationSql(buildSql, page.getPageIndex(), page.getPageSize());
	}

	private static IDialect getDialect(String dbType, String dialectClazz) throws Exception {
		IDialect dialect = null;
		if (StringUtils.isNotEmpty(dialectClazz))
			try {
				Class clazz = Class.forName(dialectClazz);
				if (IDialect.class.isAssignableFrom(clazz))
					dialect = (IDialect) clazz.newInstance();
			} catch (ClassNotFoundException e) {
				throw new Exception("Class :" + dialectClazz + " is not found");
			}
		else if (null != dbType) {
			dialect = getDialectByDbtype(dbType);
		}

		if (dialect == null) {
			throw new Exception("The value of the dialect property in mybatis configuration.xml is not defined.");
		}
		return dialect;
	}

	private static IDialect getDialectByDbtype(String dbType) {
		IDialect dialect = null;
		if ("mysql".equals(dbType.toLowerCase())) {
			dialect = MySqlDialect.INSTANCE;
		}
		if ("oracle".equals(dbType.toLowerCase())) {
			dialect = OracleDialect.INSTANCE;
		}
		if ("sqlserver".equals(dbType.toLowerCase())) {
			dialect = SQLServerDialect.INSTANCE;
		}
		if ("sqlserver2005".equals(dbType.toLowerCase())) {
			dialect = SQLServer2005Dialect.INSTANCE;
		}
		if ("db2".equals(dbType.toLowerCase())) {
			dialect = DB2Dialect.INSTANCE;
		}
		if ("sqlite".equals(dbType.toLowerCase())) {
			dialect = SQLiteDialect.INSTANCE;
		}
		if ("hsql".equals(dbType.toLowerCase())) {
			dialect = HSQLDialect.INSTANCE;
		}
		if ("postgre".equals(dbType.toLowerCase())) {
			dialect = PostgreDialect.INSTANCE;
		}
		if ("h2".equals(dbType.toLowerCase())) {
			dialect = H2Dialect.INSTANCE;
		}
		return dialect;
	}
}

package com.leaf.mybatis.support.plugins.dialects;

import org.apache.commons.lang3.StringUtils;

import com.leaf.boot.mybatis.support.plugins.pagination.IDialect;

public class SQLServer2005Dialect implements IDialect {
	public static final SQLServer2005Dialect INSTANCE = new SQLServer2005Dialect();

	private static String getOrderByPart(String sql) {
		String loweredString = sql.toLowerCase();
		int orderByIndex = loweredString.indexOf("order by");
		if (orderByIndex != -1) {
			return sql.substring(orderByIndex);
		}
		return "";
	}

	public String buildPaginationSql(String originalSql, int offset, int limit) {
		StringBuilder pagingBuilder = new StringBuilder();
		String orderby = getOrderByPart(originalSql);
		String distinctStr = "";

		String loweredString = originalSql.toLowerCase();
		String sqlPartString = originalSql;
		if (loweredString.trim().startsWith("select")) {
			int index = 6;
			if (loweredString.startsWith("select distinct")) {
				distinctStr = "DISTINCT ";
				index = 15;
			}
			sqlPartString = sqlPartString.substring(index);
		}
		pagingBuilder.append(sqlPartString);

		if (StringUtils.isEmpty(orderby)) {
			orderby = "ORDER BY CURRENT_TIMESTAMP";
		}

		StringBuilder sql = new StringBuilder();
		sql.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ")
				.append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingBuilder)
				.append(") SELECT * FROM query WHERE __row_number__ BETWEEN ").append((offset - 1) * limit + 1)
				.append(" AND ").append(offset * limit).append(" ORDER BY __row_number__");
		return sql.toString();
	}
}

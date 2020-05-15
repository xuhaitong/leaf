package com.leaf.mybatis.support.plugins.dialects;

import com.leaf.boot.mybatis.support.plugins.pagination.IDialect;

public class MySqlDialect implements IDialect {
	public static final MySqlDialect INSTANCE = new MySqlDialect();

	public String buildPaginationSql(String originalSql, int offset, int limit) {
		StringBuilder sql = new StringBuilder(originalSql);
		sql.append(" LIMIT ").append(String.valueOf((offset - 1) * limit)).append(",").append(limit);
		return sql.toString();
	}
}

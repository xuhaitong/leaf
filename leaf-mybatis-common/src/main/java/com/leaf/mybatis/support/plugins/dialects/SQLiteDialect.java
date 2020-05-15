package com.leaf.mybatis.support.plugins.dialects;

import com.leaf.boot.mybatis.support.plugins.pagination.IDialect;

public class SQLiteDialect implements IDialect {
	public static final SQLiteDialect INSTANCE = new SQLiteDialect();

	public String buildPaginationSql(String originalSql, int offset, int limit) {
		StringBuilder sql = new StringBuilder(originalSql);
		sql.append(" limit ").append(offset * limit).append(" offset ").append(String.valueOf((offset - 1) * limit));
		return sql.toString();
	}
}

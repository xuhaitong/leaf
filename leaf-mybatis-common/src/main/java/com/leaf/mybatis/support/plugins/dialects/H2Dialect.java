package com.leaf.mybatis.support.plugins.dialects;

import com.leaf.boot.mybatis.support.plugins.pagination.IDialect;

public class H2Dialect implements IDialect {
	public static final H2Dialect INSTANCE = new H2Dialect();

	public String buildPaginationSql(String originalSql, int offset, int limit) {
		StringBuilder sql = new StringBuilder(originalSql);
		sql.append(" limit ").append(String.valueOf(offset * limit));
		if (offset > 0) {
			sql.append(" offset ").append(String.valueOf((offset - 1) * limit));
		}
		return sql.toString();
	}
}

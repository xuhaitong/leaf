package com.leaf.mybatis.support.plugins.dialects;

import com.leaf.boot.mybatis.support.plugins.pagination.IDialect;

public class SQLServerDialect implements IDialect {
	public static final SQLServerDialect INSTANCE = new SQLServerDialect();

	public String buildPaginationSql(String originalSql, int offset, int limit) {
		StringBuilder sql = new StringBuilder(originalSql);
		sql.append(" OFFSET ").append(String.valueOf((offset - 1) * limit)).append(" ROWS FETCH NEXT ");
		sql.append(String.valueOf(offset * limit)).append(" ROWS ONLY");
		return sql.toString();
	}
}

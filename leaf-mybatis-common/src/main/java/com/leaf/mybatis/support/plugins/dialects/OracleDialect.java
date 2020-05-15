package com.leaf.mybatis.support.plugins.dialects;

import com.leaf.boot.mybatis.support.plugins.pagination.IDialect;

public class OracleDialect implements IDialect {
	public static final OracleDialect INSTANCE = new OracleDialect();

	public String buildPaginationSql(String originalSql, int offset, int limit) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ( SELECT TMP.*, ROWNUM ROW_ID FROM ( ");
		sql.append(originalSql).append(" ) TMP WHERE ROWNUM <=").append(offset * limit);
		sql.append(") WHERE ROW_ID > ").append(String.valueOf((offset - 1) * limit));
		return sql.toString();
	}
}

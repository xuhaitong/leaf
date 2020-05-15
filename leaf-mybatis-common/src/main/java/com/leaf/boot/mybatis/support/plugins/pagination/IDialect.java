package com.leaf.boot.mybatis.support.plugins.pagination;

public abstract interface IDialect {
	public abstract String buildPaginationSql(String paramString, int paramInt1, int paramInt2);
}

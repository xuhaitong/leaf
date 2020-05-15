package com.leaf.mybatis.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mybatis.pagination")
public class PaginationProperties {
	public static final String PAGINATION_PREFIX = "mybatis.pagination";
	private String dialect;
	private String pageSqlRegular;

	public String getDialect() {
		return this.dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getPageSqlRegular() {
		return this.pageSqlRegular;
	}

	public void setPageSqlRegular(String pageSqlRegular) {
		this.pageSqlRegular = pageSqlRegular;
	}
}

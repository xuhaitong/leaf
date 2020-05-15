package com.leaf.boot.mybatis.support.plugins;

import java.util.Properties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;

@Intercepts({
		@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.statement.StatementHandler.class, method = "query", args = {
				java.sql.Statement.class, org.apache.ibatis.session.ResultHandler.class }),
		@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.statement.StatementHandler.class, method = "update", args = {
				java.sql.Statement.class }),
		@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.statement.StatementHandler.class, method = "batch", args = {
				java.sql.Statement.class }) })
public class PerformanceInterceptor implements Interceptor {
	public Object intercept(Invocation invocation) throws Throwable {
		return null;
	}

	public Object plugin(Object target) {
		return null;
	}

	public void setProperties(Properties properties) {
	}
}

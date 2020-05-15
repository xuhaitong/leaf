package com.leaf.boot.mybatis.support.plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import com.leaf.boot.mybatis.support.plugins.pagination.DialectFactory;
import com.leaf.boot.mybatis.support.plugins.pagination.Pagination;

@Intercepts({
		@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.statement.StatementHandler.class, method = "prepare", args = {
				Connection.class, java.lang.Integer.class }) })
@Order(1)
public class PaginationInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);

	private static String pageSqlId = "";
	private String dialectType;

	public Object intercept(Invocation invocation) throws Throwable {
		if ((invocation.getTarget() instanceof RoutingStatementHandler)) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

			MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

			if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
				return invocation.proceed();
			}

			if (mappedStatement.getId().matches(pageSqlId)) {
				BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
				Object parameterObject = boundSql.getParameterObject();
				if (parameterObject == null) {
					return invocation.proceed();
				}
				Pagination pagination = null;
				if ((parameterObject instanceof Pagination)) {
					pagination = (Pagination) parameterObject;
				} else if ((parameterObject instanceof Map)) {
					for (Map.Entry entry : (Set<Map.Entry>) ((Map) parameterObject).entrySet())
						if ((entry.getValue() instanceof Pagination)) {
							pagination = (Pagination) entry.getValue();
							break;
						}
				} else {
					pagination = (Pagination) PluginUtils.getValueByFieldType(parameterObject, Pagination.class);
					if (pagination == null) {
						return invocation.proceed();
					}
				}
				if (pagination == null) {
					return invocation.proceed();
				}
				String buildSql = boundSql.getSql();
				Connection connection = (Connection) invocation.getArgs()[0];
				queryTotal(buildSql, connection, mappedStatement, boundSql, parameterObject, pagination);

				String originalSql = DialectFactory.buildPaginationSql(pagination, buildSql,
						this.dialectType.trim().toLowerCase());
				metaObject.setValue("delegate.boundSql.sql", originalSql);
			}
		}

		return invocation.proceed();
	}

	private void queryTotal(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject, Pagination<?> pagination) throws SQLException {
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(1) from ( ");
		countSql.append(sql);
		countSql.append(" ) TMP_COUNT");
		try {
			PreparedStatement statement = connection.prepareStatement(countSql.toString());
			Throwable localThrowable6 = null;
			try {
				DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
						boundSql.getParameterObject(), boundSql);
				parameterHandler.setParameters(statement);
				Long total = Long.valueOf(0L);
				ResultSet resultSet = statement.executeQuery();
				Throwable localThrowable7 = null;
				try {
					if (resultSet.next())
						total = Long.valueOf(resultSet.getLong(1));
				} catch (Throwable localThrowable1) {
					localThrowable7 = localThrowable1;
					throw localThrowable1;
				} finally {
					if (resultSet != null)
						if (localThrowable7 != null)
							try {
								resultSet.close();
							} catch (Throwable localThrowable2) {
								localThrowable7.addSuppressed(localThrowable2);
							}
						else
							resultSet.close();
				}
				pagination.setRowCount(total.intValue());
			} catch (Throwable localThrowable4) {
				localThrowable6 = localThrowable4;
				throw localThrowable4;
			} finally {
				if (statement != null)
					if (localThrowable6 != null)
						try {
							statement.close();
						} catch (Throwable localThrowable5) {
							localThrowable6.addSuppressed(localThrowable5);
						}
					else
						statement.close();
			}
		} catch (Exception e) {
			logger.error("Error: Method queryTotal execution error !", e);
		} finally {
		}
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties p) {
		this.dialectType = p.getProperty("dialect");
		if (StringUtils.isBlank(this.dialectType)) {
			try {
				throw new PropertyException("dialectName or dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (StringUtils.isBlank(pageSqlId))
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
	}
}

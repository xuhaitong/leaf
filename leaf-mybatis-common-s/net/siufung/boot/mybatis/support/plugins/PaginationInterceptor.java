/*     */ package net.siufung.boot.mybatis.support.plugins;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Properties;
/*     */ import javax.xml.bind.PropertyException;
/*     */ import net.siufung.boot.mybatis.support.plugins.pagination.DialectFactory;
/*     */ import net.siufung.boot.mybatis.support.plugins.pagination.Pagination;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.ibatis.executor.statement.RoutingStatementHandler;
/*     */ import org.apache.ibatis.mapping.BoundSql;
/*     */ import org.apache.ibatis.mapping.MappedStatement;
/*     */ import org.apache.ibatis.mapping.SqlCommandType;
/*     */ import org.apache.ibatis.plugin.Interceptor;
/*     */ import org.apache.ibatis.plugin.Intercepts;
/*     */ import org.apache.ibatis.plugin.Invocation;
/*     */ import org.apache.ibatis.plugin.Plugin;
/*     */ import org.apache.ibatis.reflection.MetaObject;
/*     */ import org.apache.ibatis.reflection.SystemMetaObject;
/*     */ import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.core.annotation.Order;
/*     */ 
/*     */ @Intercepts({@org.apache.ibatis.plugin.Signature(type=org.apache.ibatis.executor.statement.StatementHandler.class, method="prepare", args={Connection.class, java.lang.Integer.class})})
/*     */ @Order(1)
/*     */ public class PaginationInterceptor
/*     */   implements Interceptor
/*     */ {
/*  55 */   private static final Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);
/*     */ 
/*  60 */   private static String pageSqlId = "";
/*     */   private String dialectType;
/*     */ 
/*     */   public Object intercept(Invocation invocation)
/*     */     throws Throwable
/*     */   {
/*  72 */     if ((invocation.getTarget() instanceof RoutingStatementHandler)) {
/*  73 */       RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();
/*  74 */       MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
/*     */ 
/*  77 */       MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
/*     */ 
/*  79 */       if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
/*  80 */         return invocation.proceed();
/*     */       }
/*     */ 
/*  83 */       if (mappedStatement.getId().matches(pageSqlId)) {
/*  84 */         BoundSql boundSql = (BoundSql)metaObject.getValue("delegate.boundSql");
/*  85 */         Object parameterObject = boundSql.getParameterObject();
/*  86 */         if (parameterObject == null) {
/*  87 */           return invocation.proceed();
/*     */         }
/*  89 */         Pagination pagination = null;
/*  90 */         if ((parameterObject instanceof Pagination)) {
/*  91 */           pagination = (Pagination)parameterObject;
/*  92 */         } else if ((parameterObject instanceof Map)) {
/*  93 */           for (Map.Entry entry : ((Map)parameterObject).entrySet())
/*  94 */             if ((entry.getValue() instanceof Pagination)) {
/*  95 */               pagination = (Pagination)entry.getValue();
/*  96 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 100 */           pagination = (Pagination)PluginUtils.getValueByFieldType(parameterObject, Pagination.class);
/* 101 */           if (pagination == null) {
/* 102 */             return invocation.proceed();
/*     */           }
/*     */         }
/* 105 */         if (pagination == null) {
/* 106 */           return invocation.proceed();
/*     */         }
/* 108 */         String buildSql = boundSql.getSql();
/* 109 */         Connection connection = (Connection)invocation.getArgs()[0];
/* 110 */         queryTotal(buildSql, connection, mappedStatement, boundSql, parameterObject, pagination);
/*     */ 
/* 113 */         String originalSql = DialectFactory.buildPaginationSql(pagination, buildSql, this.dialectType.trim().toLowerCase());
/* 114 */         metaObject.setValue("delegate.boundSql.sql", originalSql);
/*     */       }
/*     */     }
/*     */ 
/* 118 */     return invocation.proceed();
/*     */   }
/*     */ 
/*     */   private void queryTotal(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject, Pagination<?> pagination) throws SQLException {
/* 122 */     StringBuilder countSql = new StringBuilder();
/* 123 */     countSql.append("select count(1) from ( ");
/* 124 */     countSql.append(sql);
/* 125 */     countSql.append(" ) TMP_COUNT");
/*     */     try { PreparedStatement statement = connection.prepareStatement(countSql.toString()); Throwable localThrowable6 = null;
/*     */       try { DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
/* 128 */         parameterHandler.setParameters(statement);
/* 129 */         Long total = Long.valueOf(0L);
/* 130 */         ResultSet resultSet = statement.executeQuery(); Throwable localThrowable7 = null;
/*     */         try { if (resultSet.next())
/* 132 */             total = Long.valueOf(resultSet.getLong(1));
/*     */         }
/*     */         catch (Throwable localThrowable1)
/*     */         {
/* 130 */           localThrowable7 = localThrowable1; throw localThrowable1;
/*     */         }
/*     */         finally
/*     */         {
/* 134 */           if (resultSet != null) if (localThrowable7 != null) try { resultSet.close(); } catch (Throwable localThrowable2) { localThrowable7.addSuppressed(localThrowable2); } else resultSet.close(); 
/*     */         }
/* 135 */         pagination.setRowCount(total.intValue());
/*     */       }
/*     */       catch (Throwable localThrowable4)
/*     */       {
/* 126 */         localThrowable6 = localThrowable4; throw localThrowable4;
/*     */       }
/*     */       finally
/*     */       {
/* 137 */         if (statement != null) if (localThrowable6 != null) try { statement.close(); } catch (Throwable localThrowable5) { localThrowable6.addSuppressed(localThrowable5); } else statement.close();  
/*     */       }
/*     */     } catch (Exception e) {
/* 138 */       logger.error("Error: Method queryTotal execution error !", e);
/*     */     }
/*     */     finally
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public Object plugin(Object target)
/*     */   {
/* 186 */     return Plugin.wrap(target, this);
/*     */   }
/*     */ 
/*     */   public void setProperties(Properties p)
/*     */   {
/* 191 */     this.dialectType = p.getProperty("dialect");
/* 192 */     if (StringUtils.isBlank(this.dialectType)) {
/*     */       try {
/* 194 */         throw new PropertyException("dialectName or dialect property is not found!");
/*     */       } catch (PropertyException e) {
/* 196 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 199 */     pageSqlId = p.getProperty("pageSqlId");
/* 200 */     if (StringUtils.isBlank(pageSqlId))
/*     */       try {
/* 202 */         throw new PropertyException("pageSqlId property is not found!");
/*     */       } catch (PropertyException e) {
/* 204 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.PaginationInterceptor
 * JD-Core Version:    0.6.0
 */
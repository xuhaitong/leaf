/*    */ package net.siufung.boot.mybatis.support.plugins;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.ibatis.plugin.Interceptor;
/*    */ import org.apache.ibatis.plugin.Intercepts;
/*    */ import org.apache.ibatis.plugin.Invocation;
/*    */ 
/*    */ @Intercepts({@org.apache.ibatis.plugin.Signature(type=org.apache.ibatis.executor.statement.StatementHandler.class, method="query", args={java.sql.Statement.class, org.apache.ibatis.session.ResultHandler.class}), @org.apache.ibatis.plugin.Signature(type=org.apache.ibatis.executor.statement.StatementHandler.class, method="update", args={java.sql.Statement.class}), @org.apache.ibatis.plugin.Signature(type=org.apache.ibatis.executor.statement.StatementHandler.class, method="batch", args={java.sql.Statement.class})})
/*    */ public class PerformanceInterceptor
/*    */   implements Interceptor
/*    */ {
/*    */   public Object intercept(Invocation invocation)
/*    */     throws Throwable
/*    */   {
/* 22 */     return null;
/*    */   }
/*    */ 
/*    */   public Object plugin(Object target)
/*    */   {
/* 28 */     return null;
/*    */   }
/*    */ 
/*    */   public void setProperties(Properties properties)
/*    */   {
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.PerformanceInterceptor
 * JD-Core Version:    0.6.0
 */
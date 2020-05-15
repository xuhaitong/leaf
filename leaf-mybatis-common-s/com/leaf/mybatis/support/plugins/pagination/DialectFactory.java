/*    */ package net.siufung.boot.mybatis.support.plugins.pagination;
/*    */ 
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.DB2Dialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.H2Dialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.HSQLDialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.MySqlDialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.OracleDialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.PostgreDialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.SQLServer2005Dialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.SQLServerDialect;
/*    */ import net.siufung.boot.mybatis.support.plugins.dialects.SQLiteDialect;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class DialectFactory
/*    */ {
/*    */   public static String buildPaginationSql(Pagination<?> page, String buildSql, String dbType)
/*    */     throws Exception
/*    */   {
/* 23 */     return getDialect(dbType, null).buildPaginationSql(buildSql, page.getPageIndex(), page.getPageSize());
/*    */   }
/*    */ 
/*    */   private static IDialect getDialect(String dbType, String dialectClazz) throws Exception {
/* 27 */     IDialect dialect = null;
/* 28 */     if (StringUtils.isNotEmpty(dialectClazz))
/*    */       try {
/* 30 */         Class clazz = Class.forName(dialectClazz);
/* 31 */         if (IDialect.class.isAssignableFrom(clazz))
/* 32 */           dialect = (IDialect)clazz.newInstance();
/*    */       }
/*    */       catch (ClassNotFoundException e) {
/* 35 */         throw new Exception("Class :" + dialectClazz + " is not found");
/*    */       }
/* 37 */     else if (null != dbType) {
/* 38 */       dialect = getDialectByDbtype(dbType);
/*    */     }
/*    */ 
/* 41 */     if (dialect == null) {
/* 42 */       throw new Exception("The value of the dialect property in mybatis configuration.xml is not defined.");
/*    */     }
/* 44 */     return dialect;
/*    */   }
/*    */ 
/*    */   private static IDialect getDialectByDbtype(String dbType) {
/* 48 */     IDialect dialect = null;
/* 49 */     if ("mysql".equals(dbType.toLowerCase())) {
/* 50 */       dialect = MySqlDialect.INSTANCE;
/*    */     }
/* 52 */     if ("oracle".equals(dbType.toLowerCase())) {
/* 53 */       dialect = OracleDialect.INSTANCE;
/*    */     }
/* 55 */     if ("sqlserver".equals(dbType.toLowerCase())) {
/* 56 */       dialect = SQLServerDialect.INSTANCE;
/*    */     }
/* 58 */     if ("sqlserver2005".equals(dbType.toLowerCase())) {
/* 59 */       dialect = SQLServer2005Dialect.INSTANCE;
/*    */     }
/* 61 */     if ("db2".equals(dbType.toLowerCase())) {
/* 62 */       dialect = DB2Dialect.INSTANCE;
/*    */     }
/* 64 */     if ("sqlite".equals(dbType.toLowerCase())) {
/* 65 */       dialect = SQLiteDialect.INSTANCE;
/*    */     }
/* 67 */     if ("hsql".equals(dbType.toLowerCase())) {
/* 68 */       dialect = HSQLDialect.INSTANCE;
/*    */     }
/* 70 */     if ("postgre".equals(dbType.toLowerCase())) {
/* 71 */       dialect = PostgreDialect.INSTANCE;
/*    */     }
/* 73 */     if ("h2".equals(dbType.toLowerCase())) {
/* 74 */       dialect = H2Dialect.INSTANCE;
/*    */     }
/* 76 */     return dialect;
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.pagination.DialectFactory
 * JD-Core Version:    0.6.0
 */
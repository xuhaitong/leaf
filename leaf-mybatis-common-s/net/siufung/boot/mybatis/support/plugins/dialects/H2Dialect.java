/*    */ package net.siufung.boot.mybatis.support.plugins.dialects;
/*    */ 
/*    */ import net.siufung.boot.mybatis.support.plugins.pagination.IDialect;
/*    */ 
/*    */ public class H2Dialect
/*    */   implements IDialect
/*    */ {
/* 15 */   public static final H2Dialect INSTANCE = new H2Dialect();
/*    */ 
/*    */   public String buildPaginationSql(String originalSql, int offset, int limit)
/*    */   {
/* 19 */     StringBuilder sql = new StringBuilder(originalSql);
/* 20 */     sql.append(" limit ").append(String.valueOf(offset * limit));
/* 21 */     if (offset > 0) {
/* 22 */       sql.append(" offset ").append(String.valueOf((offset - 1) * limit));
/*    */     }
/* 24 */     return sql.toString();
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.dialects.H2Dialect
 * JD-Core Version:    0.6.0
 */
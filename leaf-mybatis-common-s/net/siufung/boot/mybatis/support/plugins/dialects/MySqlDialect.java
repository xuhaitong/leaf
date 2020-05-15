/*    */ package net.siufung.boot.mybatis.support.plugins.dialects;
/*    */ 
/*    */ import net.siufung.boot.mybatis.support.plugins.pagination.IDialect;
/*    */ 
/*    */ public class MySqlDialect
/*    */   implements IDialect
/*    */ {
/* 15 */   public static final MySqlDialect INSTANCE = new MySqlDialect();
/*    */ 
/*    */   public String buildPaginationSql(String originalSql, int offset, int limit)
/*    */   {
/* 19 */     StringBuilder sql = new StringBuilder(originalSql);
/* 20 */     sql.append(" LIMIT ").append(String.valueOf((offset - 1) * limit)).append(",").append(limit);
/* 21 */     return sql.toString();
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.dialects.MySqlDialect
 * JD-Core Version:    0.6.0
 */
/*    */ package net.siufung.boot.mybatis.support.plugins.dialects;
/*    */ 
/*    */ import net.siufung.boot.mybatis.support.plugins.pagination.IDialect;
/*    */ 
/*    */ public class OracleDialect
/*    */   implements IDialect
/*    */ {
/* 12 */   public static final OracleDialect INSTANCE = new OracleDialect();
/*    */ 
/*    */   public String buildPaginationSql(String originalSql, int offset, int limit)
/*    */   {
/* 16 */     StringBuilder sql = new StringBuilder();
/* 17 */     sql.append("SELECT * FROM ( SELECT TMP.*, ROWNUM ROW_ID FROM ( ");
/* 18 */     sql.append(originalSql).append(" ) TMP WHERE ROWNUM <=").append(offset * limit);
/* 19 */     sql.append(") WHERE ROW_ID > ").append(String.valueOf((offset - 1) * limit));
/* 20 */     return sql.toString();
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.dialects.OracleDialect
 * JD-Core Version:    0.6.0
 */
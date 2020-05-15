/*    */ package net.siufung.boot.mybatis.support.plugins.dialects;
/*    */ 
/*    */ import net.siufung.boot.mybatis.support.plugins.pagination.IDialect;
/*    */ 
/*    */ public class SQLServerDialect
/*    */   implements IDialect
/*    */ {
/* 15 */   public static final SQLServerDialect INSTANCE = new SQLServerDialect();
/*    */ 
/*    */   public String buildPaginationSql(String originalSql, int offset, int limit)
/*    */   {
/* 19 */     StringBuilder sql = new StringBuilder(originalSql);
/* 20 */     sql.append(" OFFSET ").append(String.valueOf((offset - 1) * limit)).append(" ROWS FETCH NEXT ");
/* 21 */     sql.append(String.valueOf(offset * limit)).append(" ROWS ONLY");
/* 22 */     return sql.toString();
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.dialects.SQLServerDialect
 * JD-Core Version:    0.6.0
 */
/*    */ package net.siufung.boot.mybatis.support.plugins.dialects;
/*    */ 
/*    */ import net.siufung.boot.mybatis.support.plugins.pagination.IDialect;
/*    */ 
/*    */ public class DB2Dialect
/*    */   implements IDialect
/*    */ {
/* 15 */   public static final DB2Dialect INSTANCE = new DB2Dialect();
/*    */ 
/*    */   private static String getRowNumber(String originalSql) {
/* 18 */     StringBuilder rownumber = new StringBuilder(50).append("rownumber() over(");
/* 19 */     int orderByIndex = originalSql.toLowerCase().indexOf("order by");
/* 20 */     if ((orderByIndex > 0) && (!hasDistinct(originalSql))) {
/* 21 */       rownumber.append(originalSql.substring(orderByIndex));
/*    */     }
/* 23 */     rownumber.append(") as rownumber_,");
/* 24 */     return rownumber.toString();
/*    */   }
/*    */ 
/*    */   private static boolean hasDistinct(String originalSql) {
/* 28 */     return originalSql.toLowerCase().contains("select distinct");
/*    */   }
/*    */ 
/*    */   public String buildPaginationSql(String originalSql, int offset, int limit)
/*    */   {
/* 33 */     int startOfSelect = originalSql.toLowerCase().indexOf("select");
/*    */ 
/* 36 */     StringBuilder pagingSelect = new StringBuilder(originalSql.length() + 100)
/* 35 */       .append(originalSql
/* 35 */       .substring(0, startOfSelect))
/* 35 */       .append("select * from ( select ")
/* 36 */       .append(getRowNumber(originalSql));
/*    */ 
/* 37 */     if (hasDistinct(originalSql))
/* 38 */       pagingSelect.append(" row_.* from ( ").append(originalSql.substring(startOfSelect)).append(" ) as row_");
/*    */     else {
/* 40 */       pagingSelect.append(originalSql.substring(startOfSelect + 6));
/*    */     }
/* 42 */     pagingSelect.append(" ) as temp_ where rownumber_ ");
/*    */ 
/* 45 */     if (offset > 0) {
/* 46 */       String endString = new StringBuilder().append(String.valueOf((offset - 1) * limit)).append("+").append(String.valueOf(offset * limit)).toString();
/* 47 */       pagingSelect.append("between ").append(String.valueOf((offset - 1) * limit)).append("+1 and ").append(endString);
/*    */     } else {
/* 49 */       pagingSelect.append("<= ").append(String.valueOf(offset * limit));
/*    */     }
/* 51 */     return pagingSelect.toString();
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.dialects.DB2Dialect
 * JD-Core Version:    0.6.0
 */
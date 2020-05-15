/*    */ package net.siufung.boot.mybatis.support.plugins.dialects;
/*    */ 
/*    */ import net.siufung.boot.mybatis.support.plugins.pagination.IDialect;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class SQLServer2005Dialect
/*    */   implements IDialect
/*    */ {
/* 17 */   public static final SQLServer2005Dialect INSTANCE = new SQLServer2005Dialect();
/*    */ 
/*    */   private static String getOrderByPart(String sql) {
/* 20 */     String loweredString = sql.toLowerCase();
/* 21 */     int orderByIndex = loweredString.indexOf("order by");
/* 22 */     if (orderByIndex != -1) {
/* 23 */       return sql.substring(orderByIndex);
/*    */     }
/* 25 */     return "";
/*    */   }
/*    */ 
/*    */   public String buildPaginationSql(String originalSql, int offset, int limit)
/*    */   {
/* 31 */     StringBuilder pagingBuilder = new StringBuilder();
/* 32 */     String orderby = getOrderByPart(originalSql);
/* 33 */     String distinctStr = "";
/*    */ 
/* 35 */     String loweredString = originalSql.toLowerCase();
/* 36 */     String sqlPartString = originalSql;
/* 37 */     if (loweredString.trim().startsWith("select")) {
/* 38 */       int index = 6;
/* 39 */       if (loweredString.startsWith("select distinct")) {
/* 40 */         distinctStr = "DISTINCT ";
/* 41 */         index = 15;
/*    */       }
/* 43 */       sqlPartString = sqlPartString.substring(index);
/*    */     }
/* 45 */     pagingBuilder.append(sqlPartString);
/*    */ 
/* 48 */     if (StringUtils.isEmpty(orderby)) {
/* 49 */       orderby = "ORDER BY CURRENT_TIMESTAMP";
/*    */     }
/*    */ 
/* 52 */     StringBuilder sql = new StringBuilder();
/* 53 */     sql.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ")
/* 54 */       .append(" ROW_NUMBER() OVER (")
/* 54 */       .append(orderby).append(") as __row_number__, ").append(pagingBuilder)
/* 55 */       .append(") SELECT * FROM query WHERE __row_number__ BETWEEN ")
/* 56 */       .append((offset - 1) * limit + 1)
/* 57 */       .append(" AND ")
/* 58 */       .append(offset * limit)
/* 58 */       .append(" ORDER BY __row_number__");
/* 59 */     return sql.toString();
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.dialects.SQLServer2005Dialect
 * JD-Core Version:    0.6.0
 */
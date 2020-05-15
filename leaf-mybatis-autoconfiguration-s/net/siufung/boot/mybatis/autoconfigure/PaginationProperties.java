/*    */ package net.siufung.boot.mybatis.autoconfigure;
/*    */ 
/*    */ import org.springframework.boot.context.properties.ConfigurationProperties;
/*    */ 
/*    */ @ConfigurationProperties(prefix="mybatis.pagination")
/*    */ public class PaginationProperties
/*    */ {
/*    */   public static final String PAGINATION_PREFIX = "mybatis.pagination";
/*    */   private String dialect;
/*    */   private String pageSqlRegular;
/*    */ 
/*    */   public String getDialect()
/*    */   {
/* 24 */     return this.dialect;
/*    */   }
/*    */ 
/*    */   public void setDialect(String dialect) {
/* 28 */     this.dialect = dialect;
/*    */   }
/*    */ 
/*    */   public String getPageSqlRegular() {
/* 32 */     return this.pageSqlRegular;
/*    */   }
/*    */ 
/*    */   public void setPageSqlRegular(String pageSqlRegular) {
/* 36 */     this.pageSqlRegular = pageSqlRegular;
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-autoconfigure\1.1.6.RELEASE\spring-boot-starter-mybatis-autoconfigure-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.autoconfigure.PaginationProperties
 * JD-Core Version:    0.6.0
 */
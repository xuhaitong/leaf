/*    */ package net.siufung.boot.mybatis.support;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PageEntity
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 23 */   private int pageNo = 1;
/*    */ 
/* 28 */   private int pageSize = 10;
/*    */   private String orderBy;
/*    */ 
/*    */   public int getPageNo()
/*    */   {
/* 37 */     return this.pageNo;
/*    */   }
/*    */ 
/*    */   public void setPageNo(int pageNo) {
/* 41 */     this.pageNo = pageNo;
/*    */   }
/*    */ 
/*    */   public int getPageSize() {
/* 45 */     return this.pageSize;
/*    */   }
/*    */ 
/*    */   public void setPageSize(int pageSize) {
/* 49 */     this.pageSize = pageSize;
/*    */   }
/*    */ 
/*    */   public String getOrderBy() {
/* 53 */     return this.orderBy;
/*    */   }
/*    */ 
/*    */   public void setOrderBy(String orderBy) {
/* 57 */     this.orderBy = orderBy;
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.PageEntity
 * JD-Core Version:    0.6.0
 */
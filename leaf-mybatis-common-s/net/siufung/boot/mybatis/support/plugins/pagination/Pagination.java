/*     */ package net.siufung.boot.mybatis.support.plugins.pagination;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Pagination<T>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private List<T> records;
/*  27 */   private Integer pageCount = Integer.valueOf(0);
/*     */ 
/*  32 */   private Integer pageSize = Integer.valueOf(10);
/*     */ 
/*  37 */   private Integer pageIndex = Integer.valueOf(1);
/*     */ 
/*  42 */   private Integer rowCount = Integer.valueOf(0);
/*     */ 
/*  47 */   private String orderBy = "";
/*     */ 
/*     */   public Pagination()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getFirstResult()
/*     */   {
/*  60 */     return (this.pageIndex.intValue() - 1) * this.pageSize.intValue();
/*     */   }
/*     */ 
/*     */   public Pagination(int pageSize, int pageIndex)
/*     */   {
/*  71 */     this.pageSize = Integer.valueOf(pageSize);
/*  72 */     this.pageIndex = Integer.valueOf(pageIndex);
/*     */   }
/*     */ 
/*     */   public Pagination(int pageSize, int pageIndex, String orderBy)
/*     */   {
/*  82 */     this.pageSize = Integer.valueOf(pageSize);
/*  83 */     this.pageIndex = Integer.valueOf(pageIndex);
/*  84 */     this.orderBy = orderBy;
/*     */   }
/*     */ 
/*     */   public Pagination(int pageIndex)
/*     */   {
/*  93 */     this.pageIndex = Integer.valueOf(pageIndex);
/*     */   }
/*     */ 
/*     */   public void setQueryResult(int rowCount, List<T> records)
/*     */   {
/* 106 */     setRowCount(rowCount);
/* 107 */     setRecords(records);
/*     */   }
/*     */ 
/*     */   public void setRowCount(int rowCount) {
/* 111 */     this.rowCount = Integer.valueOf(rowCount);
/* 112 */     setPageCount(this.rowCount.intValue() % this.pageSize.intValue() == 0 ? this.rowCount.intValue() / this.pageSize.intValue() : this.rowCount.intValue() / this.pageSize.intValue() + 1);
/*     */   }
/*     */ 
/*     */   public int getRowCount() {
/* 116 */     return this.rowCount.intValue();
/*     */   }
/*     */ 
/*     */   public List<T> getRecords() {
/* 120 */     return this.records;
/*     */   }
/*     */ 
/*     */   public void setRecords(List<T> records) {
/* 124 */     this.records = records;
/*     */   }
/*     */ 
/*     */   public int getPageIndex()
/*     */   {
/* 129 */     return this.pageIndex.intValue();
/*     */   }
/*     */ 
/*     */   public void setPageIndex(int pageIndex) {
/* 133 */     this.pageIndex = Integer.valueOf(pageIndex);
/*     */   }
/*     */ 
/*     */   public int getPageCount() {
/* 137 */     return this.pageCount.intValue();
/*     */   }
/*     */ 
/*     */   public void setPageCount(int pageCount) {
/* 141 */     this.pageCount = Integer.valueOf(pageCount);
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 145 */     return this.pageSize.intValue();
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 149 */     this.pageSize = Integer.valueOf(pageSize);
/*     */   }
/*     */ 
/*     */   public String getOrderBy() {
/* 153 */     return this.orderBy;
/*     */   }
/*     */ 
/*     */   public void setOrderBy(String orderBy) {
/* 157 */     this.orderBy = orderBy;
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.pagination.Pagination
 * JD-Core Version:    0.6.0
 */
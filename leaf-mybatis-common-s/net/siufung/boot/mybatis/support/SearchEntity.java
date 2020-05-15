/*     */ package net.siufung.boot.mybatis.support;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonFormat;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import org.hibernate.validator.constraints.Length;
/*     */ import org.hibernate.validator.constraints.NotEmpty;
/*     */ 
/*     */ public class SearchEntity<T extends SearchEntity<T>> extends PageEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6634450282021942795L;
/*     */   private String id;
/*     */ 
/*     */   @Length(min=0, max=255, message="备注信息名称长度不能超过255个字符")
/*     */   private String remarks;
/*     */ 
/*     */   @NotEmpty(message="创建人不能为空")
/*     */   private String createBy;
/*     */ 
/*     */   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
/*     */   private Date createDate;
/*     */   private String updateBy;
/*     */ 
/*     */   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
/*     */   private Date updateDate;
/*     */   private String delFlag;
/*     */   private Integer bak1;
/*     */   private String bak2;
/*     */   private Date bak3;
/*     */ 
/*     */   public String getId()
/*     */   {
/*  60 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  64 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getRemarks() {
/*  68 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks) {
/*  72 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public String getCreateBy() {
/*  76 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy) {
/*  80 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate() {
/*  84 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate) {
/*  88 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy() {
/*  92 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy) {
/*  96 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate() {
/* 100 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate) {
/* 104 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getDelFlag() {
/* 108 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag) {
/* 112 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   public Integer getBak1() {
/* 116 */     return this.bak1;
/*     */   }
/*     */ 
/*     */   public void setBak1(Integer bak1) {
/* 120 */     this.bak1 = bak1;
/*     */   }
/*     */ 
/*     */   public String getBak2() {
/* 124 */     return this.bak2;
/*     */   }
/*     */ 
/*     */   public void setBak2(String bak2) {
/* 128 */     this.bak2 = bak2;
/*     */   }
/*     */ 
/*     */   public Date getBak3() {
/* 132 */     return this.bak3;
/*     */   }
/*     */ 
/*     */   public void setBak3(Date bak3) {
/* 136 */     this.bak3 = bak3;
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.SearchEntity
 * JD-Core Version:    0.6.0
 */
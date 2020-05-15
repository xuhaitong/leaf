/*     */ package net.siufung.boot.mybatis.support;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonFormat;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import org.hibernate.validator.constraints.Length;
/*     */ import org.hibernate.validator.constraints.NotEmpty;
/*     */ 
/*     */ public class BaseEntity<T extends BaseEntity<T>>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
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
/*  55 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  59 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getRemarks() {
/*  63 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks) {
/*  67 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public String getCreateBy() {
/*  71 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy) {
/*  75 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate() {
/*  79 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate) {
/*  83 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/*  88 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy) {
/*  92 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate() {
/*  96 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate) {
/* 100 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 105 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag) {
/* 109 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   public Integer getBak1() {
/* 113 */     return this.bak1;
/*     */   }
/*     */ 
/*     */   public void setBak1(Integer bak1) {
/* 117 */     this.bak1 = bak1;
/*     */   }
/*     */ 
/*     */   public String getBak2() {
/* 121 */     return this.bak2;
/*     */   }
/*     */ 
/*     */   public void setBak2(String bak2) {
/* 125 */     this.bak2 = bak2;
/*     */   }
/*     */ 
/*     */   public Date getBak3() {
/* 129 */     return this.bak3;
/*     */   }
/*     */ 
/*     */   public void setBak3(Date bak3) {
/* 133 */     this.bak3 = bak3;
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.BaseEntity
 * JD-Core Version:    0.6.0
 */
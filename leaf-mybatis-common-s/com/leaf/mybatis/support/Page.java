/*     */ package net.siufung.boot.mybatis.support;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Page<T>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  21 */   private int pageNo = 1;
/*     */ 
/*  24 */   private int pageSize = 10;
/*     */   private long count;
/*     */   private int first;
/*     */   private int last;
/*     */   private int prev;
/*     */   private int next;
/*     */   private boolean firstPage;
/*     */   private boolean lastPage;
/*  48 */   private int length = 10;
/*     */ 
/*  51 */   private int slider = 1;
/*     */ 
/*  53 */   private List<T> list = Lists.newArrayList();
/*     */ 
/*  56 */   private String orderBy = "";
/*     */ 
/*  59 */   private String funcName = "page";
/*     */ 
/*  62 */   private String funcParam = "";
/*     */ 
/*  65 */   private String message = "";
/*     */ 
/*     */   public Page(long count, List<T> list)
/*     */   {
/*  73 */     this.count = count;
/*  74 */     this.list = list;
/*  75 */     initialize();
/*     */   }
/*     */ 
/*     */   public Page(int pageNo, int pageSize, long count, List<T> list)
/*     */   {
/*  86 */     this.pageNo = pageNo;
/*  87 */     this.pageSize = pageSize;
/*  88 */     this.count = count;
/*  89 */     this.list = list;
/*  90 */     initialize();
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/*  99 */     this.first = 1;
/*     */ 
/* 101 */     this.last = (int)(this.count / (this.pageSize < 1 ? 20 : this.pageSize) + this.first - 1L);
/*     */ 
/* 103 */     if ((this.count % this.pageSize != 0L) || (this.last == 0)) {
/* 104 */       this.last += 1;
/*     */     }
/*     */ 
/* 107 */     if (this.last < this.first) {
/* 108 */       this.last = this.first;
/*     */     }
/*     */ 
/* 111 */     if (this.pageNo <= 1) {
/* 112 */       this.pageNo = this.first;
/* 113 */       this.firstPage = true;
/*     */     }
/*     */ 
/* 116 */     if (this.pageNo >= this.last) {
/* 117 */       this.pageNo = this.last;
/* 118 */       this.lastPage = true;
/*     */     }
/*     */ 
/* 121 */     if (this.pageNo < this.last - 1)
/* 122 */       this.next = (this.pageNo + 1);
/*     */     else {
/* 124 */       this.next = this.last;
/*     */     }
/*     */ 
/* 127 */     if (this.pageNo > 1)
/* 128 */       this.prev = (this.pageNo - 1);
/*     */     else {
/* 130 */       this.prev = this.first;
/*     */     }
/*     */ 
/* 133 */     if (this.pageNo < this.first) {
/* 134 */       this.pageNo = this.first;
/*     */     }
/*     */ 
/* 137 */     if (this.pageNo > this.last)
/* 138 */       this.pageNo = this.last;
/*     */   }
/*     */ 
/*     */   public int getPageNo()
/*     */   {
/* 144 */     return this.pageNo;
/*     */   }
/*     */ 
/*     */   public void setPageNo(int pageNo) {
/* 148 */     this.pageNo = pageNo;
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 152 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 156 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public long getCount() {
/* 160 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(long count) {
/* 164 */     this.count = count;
/* 165 */     if (this.pageSize >= count)
/* 166 */       this.pageNo = 1;
/*     */   }
/*     */ 
/*     */   public int getFirst()
/*     */   {
/* 171 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(int first) {
/* 175 */     this.first = first;
/*     */   }
/*     */ 
/*     */   public int getLast() {
/* 179 */     return this.last;
/*     */   }
/*     */ 
/*     */   public void setLast(int last) {
/* 183 */     this.last = last;
/*     */   }
/*     */ 
/*     */   public int getPrev() {
/* 187 */     return this.prev;
/*     */   }
/*     */ 
/*     */   public void setPrev(int prev) {
/* 191 */     this.prev = prev;
/*     */   }
/*     */ 
/*     */   public int getNext() {
/* 195 */     return this.next;
/*     */   }
/*     */ 
/*     */   public void setNext(int next) {
/* 199 */     this.next = next;
/*     */   }
/*     */ 
/*     */   public boolean isFirstPage() {
/* 203 */     return this.firstPage;
/*     */   }
/*     */ 
/*     */   public void setFirstPage(boolean firstPage) {
/* 207 */     this.firstPage = firstPage;
/*     */   }
/*     */ 
/*     */   public boolean isLastPage() {
/* 211 */     return this.lastPage;
/*     */   }
/*     */ 
/*     */   public void setLastPage(boolean lastPage) {
/* 215 */     this.lastPage = lastPage;
/*     */   }
/*     */ 
/*     */   public int getLength() {
/* 219 */     return this.length;
/*     */   }
/*     */ 
/*     */   public void setLength(int length) {
/* 223 */     this.length = length;
/*     */   }
/*     */ 
/*     */   public int getSlider() {
/* 227 */     return this.slider;
/*     */   }
/*     */ 
/*     */   public void setSlider(int slider) {
/* 231 */     this.slider = slider;
/*     */   }
/*     */ 
/*     */   public List<T> getList() {
/* 235 */     return this.list;
/*     */   }
/*     */ 
/*     */   public void setList(List<T> list) {
/* 239 */     this.list = list;
/*     */   }
/*     */ 
/*     */   public String getOrderBy() {
/* 243 */     return this.orderBy;
/*     */   }
/*     */ 
/*     */   public void setOrderBy(String orderBy) {
/* 247 */     this.orderBy = orderBy;
/*     */   }
/*     */ 
/*     */   public String getFuncName() {
/* 251 */     return this.funcName;
/*     */   }
/*     */ 
/*     */   public void setFuncName(String funcName) {
/* 255 */     this.funcName = funcName;
/*     */   }
/*     */ 
/*     */   public String getFuncParam() {
/* 259 */     return this.funcParam;
/*     */   }
/*     */ 
/*     */   public void setFuncParam(String funcParam) {
/* 263 */     this.funcParam = funcParam;
/*     */   }
/*     */ 
/*     */   public String getMessage() {
/* 267 */     return this.message;
/*     */   }
/*     */ 
/*     */   public void setMessage(String message) {
/* 271 */     this.message = message;
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.Page
 * JD-Core Version:    0.6.0
 */
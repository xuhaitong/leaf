/*     */ package net.siufung.boot.mybatis.support;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.siufung.boot.mybatis.support.plugins.pagination.Pagination;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Transactional(readOnly=true)
/*     */ public class ServiceImpl<M extends BaseMapper<T>, T>
/*     */   implements IService<T>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   protected M sqlTemplate;
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void insert(T entity)
/*     */   {
/*  39 */     this.sqlTemplate.insert(entity);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void insertBatch(List<T> entityList)
/*     */   {
/*  51 */     this.sqlTemplate.insertBatch(entityList);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void insertBatch(List<T> entityList, int batchSize)
/*     */   {
/*  64 */     List lists = averageAssign(entityList, batchSize);
/*  65 */     for (List list : lists)
/*  66 */       this.sqlTemplate.insertBatch(list);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void deleteById(Serializable id)
/*     */   {
/*  79 */     this.sqlTemplate.deleteById(id);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void delete(T entity)
/*     */   {
/*  90 */     this.sqlTemplate.delete(entity);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void deleteBatchIds(Collection<? extends Serializable> idList)
/*     */   {
/* 102 */     this.sqlTemplate.deleteBatchIds(idList);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void deleteBatchIds(Collection<? extends Serializable> idList, int batchSize)
/*     */   {
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void deleteByMap(Map<String, Object> columnMap)
/*     */   {
/* 127 */     this.sqlTemplate.deleteByMap(columnMap);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void update(T entity)
/*     */   {
/* 139 */     this.sqlTemplate.update(entity);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void updateBatchIds(List<T> entityList)
/*     */   {
/* 151 */     this.sqlTemplate.updateBatchIds(entityList);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void updateBatchIds(List<T> entityList, int batchSize)
/*     */   {
/* 164 */     List lists = averageAssign(entityList, batchSize);
/* 165 */     for (List list : lists)
/* 166 */       this.sqlTemplate.updateBatchIds(list);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=false, rollbackFor={Exception.class})
/*     */   public void insertOrUpdate(T entity)
/*     */   {
/*     */   }
/*     */ 
/*     */   public T selectById(Serializable id)
/*     */   {
/* 191 */     return this.sqlTemplate.selectById(id);
/*     */   }
/*     */ 
/*     */   public List<T> selectBatchIds(Collection<? extends Serializable> idList)
/*     */   {
/* 203 */     return this.sqlTemplate.selectBatchIds(idList);
/*     */   }
/*     */ 
/*     */   public List<T> selectByMap(Map<String, Object> columnMap)
/*     */   {
/* 215 */     return this.sqlTemplate.selectByMap(columnMap);
/*     */   }
/*     */ 
/*     */   public T selectOne(T entity)
/*     */   {
/* 227 */     return this.sqlTemplate.selectOne(entity);
/*     */   }
/*     */ 
/*     */   public T selectMap(Map<String, Object> columnMap)
/*     */   {
/* 239 */     return null;
/*     */   }
/*     */ 
/*     */   public long selectCount(T entity)
/*     */   {
/* 251 */     return this.sqlTemplate.selectCount(entity);
/*     */   }
/*     */ 
/*     */   public List<T> selectList(T entity)
/*     */   {
/* 263 */     return this.sqlTemplate.selectList(entity);
/*     */   }
/*     */ 
/*     */   public Pagination<T> getPage(Map<String, Object> paramsMap, PageEntity pageEntity)
/*     */   {
/* 276 */     Pagination pager = new Pagination(pageEntity.getPageSize(), pageEntity.getPageNo(), pageEntity.getOrderBy());
/* 277 */     Map map = new HashMap();
/* 278 */     map.put("pager", pager);
/* 279 */     map.put("o", paramsMap);
/* 280 */     List objectList = this.sqlTemplate.queryPage(map);
/* 281 */     pager.setRecords(objectList);
/* 282 */     return pager;
/*     */   }
/*     */ 
/*     */   public Pagination<T> getPage(T entity, PageEntity pageEntity)
/*     */   {
/* 295 */     Pagination pager = new Pagination(pageEntity.getPageSize(), pageEntity.getPageNo(), pageEntity.getOrderBy());
/* 296 */     Map map = new HashMap();
/* 297 */     map.put("pager", pager);
/* 298 */     map.put("o", entity);
/* 299 */     List objectList = this.sqlTemplate.queryPage(map);
/* 300 */     pager.setRecords(objectList);
/* 301 */     return pager;
/*     */   }
/*     */ 
/*     */   public Page<T> queryForPage(T entity, PageEntity pageEntity)
/*     */   {
/* 313 */     return null;
/*     */   }
/*     */ 
/*     */   public Page<T> queryForPage(T entity)
/*     */   {
/* 324 */     return null;
/*     */   }
/*     */ 
/*     */   private List<List<T>> averageAssign(List<T> source, int n)
/*     */   {
/* 335 */     List result = new ArrayList();
/* 336 */     int remaider = source.size() % n;
/* 337 */     int number = source.size() / n;
/* 338 */     int offset = 0;
/* 339 */     for (int i = 0; i < n; i++) {
/* 340 */       List value = null;
/* 341 */       if (remaider > 0) {
/* 342 */         value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
/* 343 */         remaider--;
/* 344 */         offset++;
/*     */       } else {
/* 346 */         value = source.subList(i * number + offset, (i + 1) * number + offset);
/*     */       }
/* 348 */       result.add(value);
/*     */     }
/* 350 */     return result;
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.ServiceImpl
 * JD-Core Version:    0.6.0
 */
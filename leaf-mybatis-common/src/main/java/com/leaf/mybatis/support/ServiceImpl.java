package com.leaf.mybatis.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.leaf.boot.mybatis.support.plugins.pagination.Pagination;

@Transactional(readOnly = true)
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

	@Autowired
	protected M sqlTemplate;

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void insert(T entity) {
		this.sqlTemplate.insert(entity);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void insertBatch(List<T> entityList) {
		this.sqlTemplate.insertBatch(entityList);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void insertBatch(List<T> entityList, int batchSize) {
		List<List<T>> lists = averageAssign(entityList, batchSize);
		for (List list : lists)
			this.sqlTemplate.insertBatch(list);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void deleteById(Serializable id) {
		this.sqlTemplate.deleteById(id);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void delete(T entity) {
		this.sqlTemplate.delete(entity);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void deleteBatchIds(Collection<? extends Serializable> idList) {
		this.sqlTemplate.deleteBatchIds(idList);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void deleteBatchIds(Collection<? extends Serializable> idList, int batchSize) {
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void deleteByMap(Map<String, Object> columnMap) {
		this.sqlTemplate.deleteByMap(columnMap);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void update(T entity) {
		this.sqlTemplate.update(entity);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void updateBatchIds(List<T> entityList) {
		this.sqlTemplate.updateBatchIds(entityList);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void updateBatchIds(List<T> entityList, int batchSize) {
		List<List<T>> lists = averageAssign(entityList, batchSize);
		for (List list : lists)
			this.sqlTemplate.updateBatchIds(list);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void insertOrUpdate(T entity) {
	}

	public T selectById(Serializable id) {
		return this.sqlTemplate.selectById(id);
	}

	public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
		return this.sqlTemplate.selectBatchIds(idList);
	}

	public List<T> selectByMap(Map<String, Object> columnMap) {
		return this.sqlTemplate.selectByMap(columnMap);
	}

	public T selectOne(T entity) {
		return this.sqlTemplate.selectOne(entity);
	}

	public T selectMap(Map<String, Object> columnMap) {
		return null;
	}

	public long selectCount(T entity) {
		return this.sqlTemplate.selectCount(entity);
	}

	public List<T> selectList(T entity) {
		return this.sqlTemplate.selectList(entity);
	}

	public Pagination<T> getPage(Map<String, Object> paramsMap, PageEntity pageEntity) {
		Pagination pager = new Pagination(pageEntity.getPageSize(), pageEntity.getPageNo(), pageEntity.getOrderBy());
		Map map = new HashMap();
		map.put("pager", pager);
		map.put("o", paramsMap);
		List objectList = this.sqlTemplate.queryPage(map);
		pager.setRecords(objectList);
		return pager;
	}

	public Pagination<T> getPage(T entity, PageEntity pageEntity) {
		Pagination pager = new Pagination(pageEntity.getPageSize(), pageEntity.getPageNo(), pageEntity.getOrderBy());
		Map map = new HashMap();
		map.put("pager", pager);
		map.put("o", entity);
		List objectList = this.sqlTemplate.queryPage(map);
		pager.setRecords(objectList);
		return pager;
	}

	public Page<T> queryForPage(T entity, PageEntity pageEntity) {
		return null;
	}

	public Page<T> queryForPage(T entity) {
		return null;
	}

	private List<List<T>> averageAssign(List<T> source, int n) {
		List result = new ArrayList();
		int remaider = source.size() % n;
		int number = source.size() / n;
		int offset = 0;
		for (int i = 0; i < n; i++) {
			List value = null;
			if (remaider > 0) {
				value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
				remaider--;
				offset++;
			} else {
				value = source.subList(i * number + offset, (i + 1) * number + offset);
			}
			result.add(value);
		}
		return result;
	}
}

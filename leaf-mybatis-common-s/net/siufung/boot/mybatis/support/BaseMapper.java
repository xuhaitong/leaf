package net.siufung.boot.mybatis.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.siufung.boot.mybatis.support.annotation.MyBatisDao;

@MyBatisDao
public abstract interface BaseMapper<T>
{
  public abstract void insert(T paramT);

  public abstract void insertBatch(List<T> paramList);

  public abstract void deleteById(Serializable paramSerializable);

  public abstract void delete(T paramT);

  public abstract void deleteByMap(Map<String, Object> paramMap);

  public abstract void deleteBatchIds(Collection<? extends Serializable> paramCollection);

  public abstract void update(T paramT);

  public abstract void updateBatchIds(List<T> paramList);

  public abstract T selectById(Serializable paramSerializable);

  public abstract List<T> selectBatchIds(Collection<? extends Serializable> paramCollection);

  public abstract List<T> selectByMap(Map<String, Object> paramMap);

  public abstract T selectOne(T paramT);

  public abstract long selectCount(T paramT);

  public abstract List<T> selectList(T paramT);

  public abstract List<T> queryPage(Map<String, Object> paramMap);
}

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.BaseMapper
 * JD-Core Version:    0.6.0
 */
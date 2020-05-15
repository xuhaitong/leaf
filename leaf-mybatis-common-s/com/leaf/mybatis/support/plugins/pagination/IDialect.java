package net.siufung.boot.mybatis.support.plugins.pagination;

public abstract interface IDialect
{
  public abstract String buildPaginationSql(String paramString, int paramInt1, int paramInt2);
}

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.plugins.pagination.IDialect
 * JD-Core Version:    0.6.0
 */
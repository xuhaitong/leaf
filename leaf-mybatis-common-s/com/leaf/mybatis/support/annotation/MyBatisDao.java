package net.siufung.boot.mybatis.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.PARAMETER})
public @interface MyBatisDao
{
  public abstract String value();
}

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-common\1.1.6.RELEASE\spring-boot-starter-mybatis-common-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.support.annotation.MyBatisDao
 * JD-Core Version:    0.6.0
 */
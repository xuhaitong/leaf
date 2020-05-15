package com.leaf.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target({ ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface LeafLog {

    /**
     * 执行的操作类型，默认为【查询】类型
     * @return
     */
	@AliasFor("type")
    LeafLogOperateType [] operationType() default LeafLogOperateType.UNKNOWN;  
    
    /**
     * 执行的操作类型，默认为【查询】类型
     * @return
     */
    @AliasFor("operationType")
    LeafLogOperateType [] type() default LeafLogOperateType.UNKNOWN;  
     
    /**
     * 执行的操作主题
     * @return
     */
    @AliasFor("name")
    String operationName() default "";
    
    /**
     * 执行的操作主题
     * @return
     */
    @AliasFor("operationName")
    String name() default "";
    
    /**
     * 执行的操作类型序号，与注解operationType（或type）一起使用
     * @return
     */
    @AliasFor("index")
    String typeIndexOf() default "0"; 
    
    /**
     * 执行的操作类型序号，与注解operationType（或type）一起使用
     * @return
     */
    @AliasFor("typeIndexOf")
    String index() default "0"; 
    
}
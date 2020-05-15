package com.leaf.log;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({LeafLogAutoConfiguration.class,LeafLogAspectImportSelector.class})
public @interface EnableLeafLog {
}

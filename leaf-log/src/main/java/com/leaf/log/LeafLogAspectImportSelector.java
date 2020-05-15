package com.leaf.log;

import com.leaf.log.aspect.LeafLogAspect;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class LeafLogAspectImportSelector implements ImportSelector{
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{LeafLogAspect.class.getName()};
    }
}

/*    */ package net.siufung.boot.mybatis.autoconfigure;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.ibatis.io.VFS;
/*    */ import org.springframework.core.io.Resource;
/*    */ import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/*    */ import org.springframework.core.io.support.ResourcePatternResolver;
/*    */ 
/*    */ public class SpringBootVFS extends VFS
/*    */ {
/*    */   private final ResourcePatternResolver resourceResolver;
/*    */ 
/*    */   public SpringBootVFS()
/*    */   {
/* 25 */     this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
/*    */   }
/*    */ 
/*    */   public boolean isValid()
/*    */   {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   protected List<String> list(URL url, String path) throws IOException
/*    */   {
/* 35 */     Resource[] resources = this.resourceResolver.getResources("classpath*:" + path + "/**/*.class");
/* 36 */     List resourcePaths = new ArrayList();
/* 37 */     for (Resource resource : resources) {
/* 38 */       resourcePaths.add(preserveSubpackageName(resource.getURI(), path));
/*    */     }
/* 40 */     return resourcePaths;
/*    */   }
/*    */ 
/*    */   private static String preserveSubpackageName(URI uri, String rootPath) {
/* 44 */     String uriStr = uri.toString();
/* 45 */     int start = uriStr.indexOf(rootPath);
/* 46 */     return uriStr.substring(start);
/*    */   }
/*    */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-autoconfigure\1.1.6.RELEASE\spring-boot-starter-mybatis-autoconfigure-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.autoconfigure.SpringBootVFS
 * JD-Core Version:    0.6.0
 */
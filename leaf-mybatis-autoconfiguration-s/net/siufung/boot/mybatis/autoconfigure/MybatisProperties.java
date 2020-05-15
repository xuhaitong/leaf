/*     */ package net.siufung.boot.mybatis.autoconfigure;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.apache.ibatis.session.Configuration;
/*     */ import org.apache.ibatis.session.ExecutorType;
/*     */ import org.springframework.boot.context.properties.ConfigurationProperties;
/*     */ import org.springframework.boot.context.properties.NestedConfigurationProperty;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/*     */ import org.springframework.core.io.support.ResourcePatternResolver;
/*     */ 
/*     */ @ConfigurationProperties(prefix="mybatis")
/*     */ public class MybatisProperties
/*     */ {
/*     */   public static final String MYBATIS_PREFIX = "mybatis";
/*     */   private String configLocation;
/*     */   private String[] mapperLocations;
/*     */   private String typeAliasesPackage;
/*     */   private String typeHandlersPackage;
/*     */   private String basePackage;
/*  57 */   private boolean checkConfigLocation = false;
/*     */   private ExecutorType executorType;
/*     */ 
/*     */   @NestedConfigurationProperty
/*     */   private Configuration configuration;
/*     */ 
/*     */   public String getConfigLocation()
/*     */   {
/*  77 */     return this.configLocation;
/*     */   }
/*     */ 
/*     */   public void setConfigLocation(String configLocation)
/*     */   {
/*  85 */     this.configLocation = configLocation;
/*     */   }
/*     */   @Deprecated
/*     */   public String getConfig() {
/*  90 */     return this.configLocation;
/*     */   }
/*     */   @Deprecated
/*     */   public void setConfig(String config) {
/*  95 */     this.configLocation = config;
/*     */   }
/*     */ 
/*     */   public String[] getMapperLocations() {
/*  99 */     return this.mapperLocations;
/*     */   }
/*     */ 
/*     */   public void setMapperLocations(String[] mapperLocations) {
/* 103 */     this.mapperLocations = mapperLocations;
/*     */   }
/*     */ 
/*     */   public String getTypeHandlersPackage() {
/* 107 */     return this.typeHandlersPackage;
/*     */   }
/*     */ 
/*     */   public void setTypeHandlersPackage(String typeHandlersPackage) {
/* 111 */     this.typeHandlersPackage = typeHandlersPackage;
/*     */   }
/*     */ 
/*     */   public String getTypeAliasesPackage() {
/* 115 */     return this.typeAliasesPackage;
/*     */   }
/*     */ 
/*     */   public void setTypeAliasesPackage(String typeAliasesPackage) {
/* 119 */     this.typeAliasesPackage = typeAliasesPackage;
/*     */   }
/*     */ 
/*     */   public String getBasePackage()
/*     */   {
/* 124 */     return this.basePackage;
/*     */   }
/*     */ 
/*     */   public void setBasePackage(String basePackage) {
/* 128 */     this.basePackage = basePackage;
/*     */   }
/*     */ 
/*     */   public boolean isCheckConfigLocation() {
/* 132 */     return this.checkConfigLocation;
/*     */   }
/*     */ 
/*     */   public void setCheckConfigLocation(boolean checkConfigLocation) {
/* 136 */     this.checkConfigLocation = checkConfigLocation;
/*     */   }
/*     */ 
/*     */   public ExecutorType getExecutorType() {
/* 140 */     return this.executorType;
/*     */   }
/*     */ 
/*     */   public void setExecutorType(ExecutorType executorType) {
/* 144 */     this.executorType = executorType;
/*     */   }
/*     */ 
/*     */   public Configuration getConfiguration() {
/* 148 */     return this.configuration;
/*     */   }
/*     */ 
/*     */   public void setConfiguration(Configuration configuration) {
/* 152 */     this.configuration = configuration;
/*     */   }
/*     */ 
/*     */   public Resource[] resolveMapperLocations() {
/* 156 */     ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
/* 157 */     List resources = new ArrayList();
/* 158 */     if (this.mapperLocations != null) {
/* 159 */       for (String mapperLocation : this.mapperLocations)
/*     */         try {
/* 161 */           Resource[] mappers = resourceResolver.getResources(mapperLocation);
/* 162 */           resources.addAll(Arrays.asList(mappers));
/*     */         }
/*     */         catch (IOException localIOException)
/*     */         {
/*     */         }
/*     */     }
/* 168 */     return (Resource[])resources.toArray(new Resource[resources.size()]);
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-autoconfigure\1.1.6.RELEASE\spring-boot-starter-mybatis-autoconfigure-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.autoconfigure.MybatisProperties
 * JD-Core Version:    0.6.0
 */
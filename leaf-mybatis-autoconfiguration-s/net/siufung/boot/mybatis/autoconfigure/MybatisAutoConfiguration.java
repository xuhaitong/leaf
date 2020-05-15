/*     */ package net.siufung.boot.mybatis.autoconfigure;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.sql.DataSource;
/*     */ import net.siufung.boot.mybatis.support.annotation.MyBatisDao;
/*     */ import net.siufung.boot.mybatis.support.plugins.PaginationInterceptor;
/*     */ import org.apache.ibatis.mapping.DatabaseIdProvider;
/*     */ import org.apache.ibatis.plugin.Interceptor;
/*     */ import org.apache.ibatis.session.ExecutorType;
/*     */ import org.apache.ibatis.session.SqlSessionFactory;
/*     */ import org.mybatis.spring.SqlSessionFactoryBean;
/*     */ import org.mybatis.spring.SqlSessionTemplate;
/*     */ import org.mybatis.spring.mapper.ClassPathMapperScanner;
/*     */ import org.mybatis.spring.mapper.MapperFactoryBean;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.BeanFactory;
/*     */ import org.springframework.beans.factory.BeanFactoryAware;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.support.BeanDefinitionRegistry;
/*     */ import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
/*     */ import org.springframework.boot.autoconfigure.AutoConfigureAfter;
/*     */ import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
/*     */ import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
/*     */ import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
/*     */ import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/*     */ import org.springframework.boot.context.properties.EnableConfigurationProperties;
/*     */ import org.springframework.context.ResourceLoaderAware;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import org.springframework.context.annotation.Import;
/*     */ import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
/*     */ import org.springframework.core.annotation.Order;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.ResourceLoader;
/*     */ import org.springframework.core.type.AnnotationMetadata;
/*     */ import org.springframework.util.Assert;
/*     */ import org.springframework.util.ObjectUtils;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ @Configuration
/*     */ @ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
/*     */ @ConditionalOnBean({DataSource.class})
/*     */ @EnableConfigurationProperties({MybatisProperties.class, PaginationProperties.class})
/*     */ @AutoConfigureAfter({DataSourceAutoConfiguration.class})
/*     */ @Order(2)
/*     */ public class MybatisAutoConfiguration
/*     */ {
/*  65 */   private static final Logger logger = LoggerFactory.getLogger(MybatisAutoConfiguration.class);
/*     */ 
/*     */   @Autowired
/*     */   private MybatisProperties mybatisProperties;
/*     */ 
/*     */   @Autowired
/*     */   private PaginationProperties PaginationProperties;
/*     */ 
/*     */   @Autowired(required=false)
/*     */   private List<Interceptor> interceptors;
/*     */ 
/*     */   @Autowired
/*     */   private ResourceLoader resourceLoader;
/*     */ 
/*     */   @Autowired(required=false)
/*     */   private DatabaseIdProvider databaseIdProvider;
/*     */ 
/*  84 */   @PostConstruct
/*     */   public void checkConfigFileExists() { if ((this.mybatisProperties.isCheckConfigLocation()) && (StringUtils.hasText(this.mybatisProperties.getConfigLocation()))) {
/*  85 */       Resource resource = this.resourceLoader.getResource(this.mybatisProperties.getConfigLocation());
/*  86 */       Assert.state(resource.exists(), "Cannot find config location: " + resource + " (please add config file or check your Mybatis configuration)");
/*     */     } }
/*     */ 
/*     */   @Bean
/*     */   @ConditionalOnMissingBean
/*     */   public PaginationInterceptor paginationPlugin() {
/*  94 */     Properties props = new Properties();
/*  95 */     props.setProperty("dialect", this.PaginationProperties.getDialect());
/*  96 */     props.setProperty("pageSqlId", this.PaginationProperties.getPageSqlRegular());
/*  97 */     PaginationInterceptor paginationPlugin = new PaginationInterceptor();
/*  98 */     paginationPlugin.setProperties(props);
/*  99 */     logger.info("PaginationPlugin Injection success ！！");
/* 100 */     return paginationPlugin;
/*     */   }
/* 106 */   @Bean
/*     */   @ConditionalOnMissingBean
/*     */   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception { SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
/* 107 */     factory.setDataSource(dataSource);
/* 108 */     factory.setVfs(SpringBootVFS.class);
/* 109 */     if (StringUtils.hasText(this.mybatisProperties.getConfigLocation())) {
/* 110 */       factory.setConfigLocation(this.resourceLoader.getResource(this.mybatisProperties.getConfigLocation()));
/*     */     }
/* 112 */     factory.setConfiguration(this.mybatisProperties.getConfiguration());
/* 113 */     if (this.databaseIdProvider != null) {
/* 114 */       factory.setDatabaseIdProvider(this.databaseIdProvider);
/*     */     }
/* 116 */     if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
/* 117 */       factory.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
/*     */     }
/* 119 */     if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
/* 120 */       factory.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
/*     */     }
/* 122 */     if (!ObjectUtils.isEmpty(this.mybatisProperties.resolveMapperLocations())) {
/* 123 */       factory.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
/*     */     }
/* 125 */     this.interceptors.add(paginationPlugin());
/* 126 */     if ((this.interceptors != null) && (this.interceptors.size() > 0)) {
/* 127 */       factory.setPlugins((Interceptor[])(Interceptor[])Iterators.toArray(this.interceptors.iterator(), Interceptor.class));
/*     */     }
/*     */ 
/* 131 */     logger.info("MyBatis Injection success ！！");
/* 132 */     return factory.getObject(); }
/*     */ 
/*     */   @Bean
/*     */   @ConditionalOnMissingBean
/*     */   public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
/* 139 */     ExecutorType executorType = this.mybatisProperties.getExecutorType();
/* 140 */     if (executorType != null) {
/* 141 */       return new SqlSessionTemplate(sqlSessionFactory, executorType);
/*     */     }
/* 143 */     return new SqlSessionTemplate(sqlSessionFactory);
/*     */   }
/*     */ 
/*     */   @Configuration
/*     */   @Import({MybatisAutoConfiguration.AutoConfiguredMapperScannerRegistrar.class})
/*     */   @ConditionalOnMissingBean({MapperFactoryBean.class})
/*     */   public static class MapperScannerRegistrarNotFoundConfiguration
/*     */   {
/*     */     @PostConstruct
/*     */     public void afterPropertiesSet()
/*     */     {
/* 200 */       MybatisAutoConfiguration.logger.debug("No {} found.", MapperFactoryBean.class.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class AutoConfiguredMapperScannerRegistrar
/*     */     implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware
/*     */   {
/*     */     private BeanFactory beanFactory;
/*     */     private ResourceLoader resourceLoader;
/*     */ 
/*     */     public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry)
/*     */     {
/* 157 */       MybatisAutoConfiguration.logger.debug("Searching for mappers annotated with @MyBatisDao");
/*     */ 
/* 159 */       ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
/*     */       try
/*     */       {
/* 162 */         if (this.resourceLoader != null) {
/* 163 */           scanner.setResourceLoader(this.resourceLoader);
/*     */         }
/*     */ 
/* 166 */         List packages = AutoConfigurationPackages.get(this.beanFactory);
/* 167 */         if (MybatisAutoConfiguration.logger.isDebugEnabled()) {
/* 168 */           for (String pkg : packages) {
/* 169 */             MybatisAutoConfiguration.logger.debug("Using auto-configuration base package '{}'", pkg);
/*     */           }
/*     */         }
/*     */ 
/* 173 */         scanner.setAnnotationClass(MyBatisDao.class);
/* 174 */         scanner.registerFilters();
/* 175 */         scanner.doScan(StringUtils.toStringArray(packages));
/*     */       } catch (IllegalStateException ex) {
/* 177 */         MybatisAutoConfiguration.logger.debug("Could not determine auto-configuration package, automatic MyBatisDao scanning disabled.", ex);
/*     */       }
/*     */     }
/*     */ 
/*     */     public void setBeanFactory(BeanFactory beanFactory) throws BeansException
/*     */     {
/* 183 */       this.beanFactory = beanFactory;
/*     */     }
/*     */ 
/*     */     public void setResourceLoader(ResourceLoader resourceLoader)
/*     */     {
/* 188 */       this.resourceLoader = resourceLoader;
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\CODING_SPACE\maven\loc_repository_cosmo\net\siufung\boot\spring-boot-starter-mybatis-autoconfigure\1.1.6.RELEASE\spring-boot-starter-mybatis-autoconfigure-1.1.6.RELEASE.jar
 * Qualified Name:     net.siufung.boot.mybatis.autoconfigure.MybatisAutoConfiguration
 * JD-Core Version:    0.6.0
 */
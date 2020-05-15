package com.leaf.mybatis.autoconfigure;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Iterators;
import com.leaf.boot.mybatis.support.plugins.PaginationInterceptor;
import com.leaf.mybatis.support.annotation.MyBatisDao;

@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
@ConditionalOnBean({ DataSource.class })
@EnableConfigurationProperties({ MybatisProperties.class, PaginationProperties.class })
@AutoConfigureAfter({ DataSourceAutoConfiguration.class })
@Order(2)
public class MybatisAutoConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MybatisAutoConfiguration.class);

	@Autowired
	private MybatisProperties mybatisProperties;

	@Autowired
	private PaginationProperties PaginationProperties;

	@Autowired(required = false)
	private List<Interceptor> interceptors;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired(required = false)
	private DatabaseIdProvider databaseIdProvider;

	@PostConstruct
	public void checkConfigFileExists() {
		if ((this.mybatisProperties.isCheckConfigLocation())
				&& (StringUtils.hasText(this.mybatisProperties.getConfigLocation()))) {
			Resource resource = this.resourceLoader.getResource(this.mybatisProperties.getConfigLocation());
			Assert.state(resource.exists(), "Cannot find config location: " + resource
					+ " (please add config file or check your Mybatis configuration)");
		}
	}

	@Bean
	@ConditionalOnMissingBean
	public PaginationInterceptor paginationPlugin() {
		Properties props = new Properties();
		props.setProperty("dialect", this.PaginationProperties.getDialect());
		props.setProperty("pageSqlId", this.PaginationProperties.getPageSqlRegular());
		PaginationInterceptor paginationPlugin = new PaginationInterceptor();
		paginationPlugin.setProperties(props);
		logger.info("PaginationPlugin Injection success ！！");
		return paginationPlugin;
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setVfs(SpringBootVFS.class);
		if (StringUtils.hasText(this.mybatisProperties.getConfigLocation())) {
			factory.setConfigLocation(this.resourceLoader.getResource(this.mybatisProperties.getConfigLocation()));
		}
		factory.setConfiguration(this.mybatisProperties.getConfiguration());
		if (this.databaseIdProvider != null) {
			factory.setDatabaseIdProvider(this.databaseIdProvider);
		}
		if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
		}
		if (!ObjectUtils.isEmpty(this.mybatisProperties.resolveMapperLocations())) {
			factory.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
		}
		this.interceptors.add(paginationPlugin());
		if ((this.interceptors != null) && (this.interceptors.size() > 0)) {
			factory.setPlugins(
					(Interceptor[]) (Interceptor[]) Iterators.toArray(this.interceptors.iterator(), Interceptor.class));
		}

		logger.info("MyBatis Injection success ！！");
		return factory.getObject();
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		ExecutorType executorType = this.mybatisProperties.getExecutorType();
		if (executorType != null) {
			return new SqlSessionTemplate(sqlSessionFactory, executorType);
		}
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Configuration
	@Import({ MybatisAutoConfiguration.AutoConfiguredMapperScannerRegistrar.class })
	@ConditionalOnMissingBean({ MapperFactoryBean.class })
	public static class MapperScannerRegistrarNotFoundConfiguration {
		@PostConstruct
		public void afterPropertiesSet() {
			MybatisAutoConfiguration.logger.debug("No {} found.", MapperFactoryBean.class.getName());
		}
	}

	public static class AutoConfiguredMapperScannerRegistrar
			implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware {
		private BeanFactory beanFactory;
		private ResourceLoader resourceLoader;

		public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
				BeanDefinitionRegistry registry) {
			MybatisAutoConfiguration.logger.debug("Searching for mappers annotated with @MyBatisDao");

			ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
			try {
				if (this.resourceLoader != null) {
					scanner.setResourceLoader(this.resourceLoader);
				}

				List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
				if (MybatisAutoConfiguration.logger.isDebugEnabled()) {
					for (String pkg : packages) {
						MybatisAutoConfiguration.logger.debug("Using auto-configuration base package '{}'", pkg);
					}
				}

				scanner.setAnnotationClass(MyBatisDao.class);
				scanner.registerFilters();
				scanner.doScan(StringUtils.toStringArray(packages));
			} catch (IllegalStateException ex) {
				MybatisAutoConfiguration.logger.debug(
						"Could not determine auto-configuration package, automatic MyBatisDao scanning disabled.", ex);
			}
		}

		public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
			this.beanFactory = beanFactory;
		}

		public void setResourceLoader(ResourceLoader resourceLoader) {
			this.resourceLoader = resourceLoader;
		}
	}
}

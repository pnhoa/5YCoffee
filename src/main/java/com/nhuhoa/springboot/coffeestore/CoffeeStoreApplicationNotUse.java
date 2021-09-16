package com.nhuhoa.springboot.coffeestore;

import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

//@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class CoffeeStoreApplicationNotUse extends SpringBootServletInitializer {
	
	@Autowired
	private Environment env;
	
	public Logger logger = Logger.getLogger(getClass().getName());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CoffeeStoreApplicationNotUse.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CoffeeStoreApplicationNotUse.class, args);
	}
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		// set properties for jdbc
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		
		logger.info(">>> Driver Class Name: " + env.getProperty("spring.datasource.driver-class-name"));
		logger.info(">>> Url: " + env.getProperty("spring.datasource.url"));
		
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		
		// put hibernate properties
		
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		properties.put("hibernate.hbm2ddl.auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.connection.CharSet",env.getProperty("spring.jpa.properties.hibernate.connection.CharSet"));
		properties.put("hibernate.connection.characterEncoding",env.getProperty("spring.jpa.properties.hibernate.connection.characterEncoding"));
	
		// inject factory
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		factoryBean.setDataSource(dataSource);
		factoryBean.setPackagesToScan(env.getProperty("spring.jpa.properties.hibernate.packagesToScan"));
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		
		SessionFactory sessionFactory = factoryBean.getObject();
		
		logger.info(">> Session factory: " + sessionFactory);
		
		return sessionFactory;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		
		transactionManager.setSessionFactory(sessionFactory);
		
		return transactionManager;
	}
	
	   private HibernateJpaVendorAdapter vendorAdaptor() {
	         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	         vendorAdapter.setShowSql(true);
	         return vendorAdapter;
	    }
	
	@Autowired
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		Properties properties = new Properties();
		
		properties.put("jpa.generate-ddl", env.getProperty("spring.jpa.generate-ddl"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		properties.put("hibernate.hbm2ddl.auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.connection.CharSet",env.getProperty("spring.jpa.properties.hibernate.connection.CharSet"));
		properties.put("hibernate.connection.characterEncoding",env.getProperty("spring.jpa.properties.hibernate.connection.characterEncoding"));
			
		
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactoryBean.setDataSource(getDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getProperty("spring.jpa.properties.hibernate.packagesToScan"));             
        entityManagerFactoryBean.setJpaProperties(properties);
		
		return entityManagerFactoryBean;
	}
	
	

}

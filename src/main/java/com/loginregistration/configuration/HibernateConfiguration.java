package com.loginregistration.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.loginregistration.model.Address;
import com.loginregistration.model.User;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration 
{
	/*@Bean("sessionFactory")
	public SessionFactory getSessionFactory()
	{
		System.out.println("1111111111111111111111111111");
		AnnotationConfiguration configuration=new AnnotationConfiguration();
		System.out.println("22222222222222222222");

		configuration.configure("hibernate.cfg.xml");
		System.out.println("33333333333333333333333333");

		return configuration.buildSessionFactory();
	}*/
	
	@Bean
	public DataSource dataSource() 
	{
		System.out.println("DATASOURCE");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/SpringBootRestAPI");
		dataSource.setUsername("root");
		dataSource.setPassword("bridgeit");
		return dataSource;
	}
 
	@Bean
	public LocalSessionFactoryBean sessionFactory() 
	{
		System.out.println("LOCALSESSIONFACTORYBEAN");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.loginregistration");
		sessionFactory.setAnnotatedClasses(Address.class);
		sessionFactory.setAnnotatedClasses(User.class);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
 
	@Bean
	public HibernateTransactionManager transactionManager() 
	{
		System.out.println("HIBERNATETRANSACTIONMANAGER");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() 
	{
		System.out.println("LOCALCONTAINERENTITYMANAGERFACTORYBEAN");
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.loginregistration" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}
}
package com.infinitivus.project.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.infinitivus.project.repository")
@EnableTransactionManagement
@PropertySources(value = {@PropertySource("classpath:db.properties")})
public class MyJPAConfig {

    @Autowired
    private Environment env;

    private static final Logger log = LoggerFactory.getLogger(MyJPAConfig.class);

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.infinitivus.project.entity");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        return factory;
    }

    Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql",env.getProperty("hibernate.format_sql"));
//        properties.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
//        properties.getProperty("hibernate.id.new_generator_mappings",env.getProperty("hibernate.id.new_generator_mappings"));
//        properties.getProperty("hibernate.enable_lazy_load_no_trans",env.getProperty("hibernate.enable_lazy_load_no_trans"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("database.driverClassName"));
        ds.setUrl(env.getProperty("database.url"));
        ds.setUsername(env.getProperty("database.username"));
        ds.setPassword(env.getProperty("database.password"));
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }
}
// hibernate configure c3po
//    @Bean
//    public DataSource dataSource() {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try {
//            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?createDatabaseIfNotExist=true");
//            dataSource.setUser("root");
//            dataSource.setPassword("admin");
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        return dataSource;
//    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("com.infinitivus.project.entity");
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        hibernateProperties.setProperty("hibernate.ddl-auto", "update");
//        sessionFactory.setHibernateProperties(hibernateProperties);
//        return sessionFactory;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }
//}

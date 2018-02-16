package br.com.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Profile("DEV")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.todoapp.models.repositories")
public class Persistence {

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean factoryBean() throws IOException {

        LocalContainerEntityManagerFactoryBean factoryBean;
        factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setPackagesToScan("br.com.todoapp.models.domains");
        factoryBean.setDataSource(dataSource());

        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(additionalProperties());


        return factoryBean;
    }


    @Bean
    public DataSource dataSource() throws IOException {

        Resource databaseResource;
        Properties databaseProperties;
        DriverManagerDataSource dataSource;

        databaseResource = new ClassPathResource("./application.properties");

        databaseProperties = PropertiesLoaderUtils.loadProperties(databaseResource);

        dataSource = new DriverManagerDataSource(
                databaseProperties.getProperty("url"),
                databaseProperties.getProperty("username"),
                databaseProperties.getProperty("password")
        );

        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){

        JpaTransactionManager transactionManager;
        transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;

    }

    private Properties additionalProperties() {

        Properties additionalProperties;
        additionalProperties = new Properties();

        additionalProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        additionalProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        additionalProperties.setProperty("hibernate.show_sql", "false");

        return additionalProperties;

    }


}

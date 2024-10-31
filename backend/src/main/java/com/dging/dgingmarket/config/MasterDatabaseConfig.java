package com.dging.dgingmarket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "dgingmarketDbEntityManager",
        transactionManagerRef = "dgingmarketDbTransactionManager",
        basePackages = {
                "com.dging.dgingmarket.domain.base",
                "com.dging.dgingmarket.domain.chat",
                "com.dging.dgingmarket.domain.common",
                "com.dging.dgingmarket.domain.product",
                "com.dging.dgingmarket.domain.refreshtoken",
                "com.dging.dgingmarket.domain.store",
                "com.dging.dgingmarket.domain.user",
        }
)
@RequiredArgsConstructor
public class MasterDatabaseConfig {

    private final Environment env;

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.dgingmarket-db")
    public DataSource dgingmarketDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager dgingmarketDbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(dgingmarketDbEntityManager().getObject());

        return transactionManager;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean dgingmarketDbEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dgingmarketDbDataSource());
        em.setPackagesToScan(
                "com.dging.dgingmarket.domain.base",
                "com.dging.dgingmarket.domain.chat",
                "com.dging.dgingmarket.domain.common",
                "com.dging.dgingmarket.domain.product",
                "com.dging.dgingmarket.domain.refreshtoken",
                "com.dging.dgingmarket.domain.store",
                "com.dging.dgingmarket.domain.user"
        );

        em.setPersistenceUnitName("dgingmarketDbEntityManager");

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.dgingmarket-db.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }
}

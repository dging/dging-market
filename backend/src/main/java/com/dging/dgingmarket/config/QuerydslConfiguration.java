package com.dging.dgingmarket.config;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfiguration {

    @PersistenceContext(unitName = "dgingmarketDbEntityManager")
    private EntityManager dgmDbEm;

    @Bean
    public JPAQueryFactory dgingMarketDbJpaQueryFactory() {
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, dgmDbEm);
    }
}

package ru.easyjava.spring.data.declarative.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configures JPA and Spring transaction management.
 */
@Configuration
@EnableTransactionManagement
public class DaoConfiguration {

    /**
     * Instanticate JPA bean.
     * @return emf wrapper bean.
     */
    @Bean
    public LocalEntityManagerFactoryBean emf() {
        LocalEntityManagerFactoryBean result =
                new LocalEntityManagerFactoryBean();
        result.setPersistenceUnitName("ru.easyjava.spring.data.transactions");
        return result;
    }

    /**
     * Instantiate transaction manager.
     * @return PlatformTransactionManager bean for JPA.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager result = new JpaTransactionManager();
        result.setEntityManagerFactory(emf().getObject());
        return result;
    }
}

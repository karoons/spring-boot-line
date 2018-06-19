/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ananda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.sql.DataSource;
import java.util.Locale;

/**
 *
 * @author Karoons
 */
@Configuration
public class DatabaseConfig {

    public DatabaseConfig() {
    }

//    @Bean(name = "dbHR")
//    @Primary
//    @ConfigurationProperties(prefix = "mitrphol-orgsys-hr.datasource")
//    public DataSource hrDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "jdbcHrService")
//    @Autowired
//    public JdbcTemplate createJdbcTemplate_HrDataSource(@Qualifier("dbHR") DataSource hrDataSource) {
//        return new JdbcTemplate(hrDataSource);
//    }
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }

}

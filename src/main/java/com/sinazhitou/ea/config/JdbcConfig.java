package com.sinazhitou.ea.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by pansen on 2018/4/4.
 */
@Configuration
public class JdbcConfig {

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.first")
  public DataSource prodDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.second")
  public DataSource devDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public JdbcOperations jdbcTemplateMt5(@Qualifier("prodDataSource") DataSource prodDataSource) {
    return new JdbcTemplate(prodDataSource);
  }

  @Bean
  public JdbcOperations jdbcTemplateZtRisk(@Qualifier("devDataSource") DataSource devDataSource) {
    return new JdbcTemplate(devDataSource);
  }

}

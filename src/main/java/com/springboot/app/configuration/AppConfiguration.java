package com.springboot.app.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
@RestController
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude =
{
  DataSourceAutoConfiguration.class
})
@ComponentScan(basePackages =
{
  "com.springboot.app.*"
})
@PropertySource("classpath:/application/application.properties")
public class AppConfiguration extends WebMvcConfigurerAdapter
{

  @Autowired
  private Environment env;


  @Override
  public void configurePathMatch(PathMatchConfigurer configurer)
  {
    configurer.setUseSuffixPatternMatch(false);
  }


  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry)
  {
    registry.addResourceHandler("/resources/**").
            addResourceLocations("/resources/").setCachePeriod(0).
            resourceChain(true).
            addResolver(new PathResourceResolver());

    registry.addResourceHandler("/webjars/**").
            addResourceLocations("/webjars/").setCachePeriod(0).
            resourceChain(true).
            addResolver(new PathResourceResolver());

  }


  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource)
  {
    return new JdbcTemplate(dataSource);
  }


  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource)
  {
    return new DataSourceTransactionManager(dataSource);
  }


  public DataSource dataSource()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(env.
            getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));

    return dataSource;
  }

}

package com.noctis.tools.core.config;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import de.neuland.jade4j.spring.view.JadeViewResolver;

/**
 * Configuration class for resource mappings, and view resolver
 * 
 * @author Duncan
 *
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

  /** Add resource handlers for context access */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/img/**").addResourceLocations("/img/**");
  }

  /**
   * Servlet container setup bean
   * 
   * @return the configured Tomcat servlet container
   */
  public @Bean EmbeddedServletContainerFactory servletContainer() {
    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
    factory.setPort(Integer.parseInt(System.getenv("PORT")));
    factory.setSessionTimeout(10, TimeUnit.MINUTES);
    factory.addErrorPages(new ErrorPage("/error"));
    return factory;
  }

  /** Jade Configuration Beans */

  /**
   * Set up the Spring template configuration for jade4j's ViewResolver.
   * BasePath is an extension of the classpath default /src/main/webapp
   * 
   * @return the configured template
   */
  public @Bean SpringTemplateLoader templateLoader() {
    SpringTemplateLoader templateLoader = new SpringTemplateLoader();
    templateLoader.setBasePath("/views/");
    templateLoader.setEncoding("UTF-8");
    templateLoader.setSuffix(".jade");
    return templateLoader;
  }

  /**
   * Set up display and templating configurations for the jade4j ViewResolver.
   * 
   * @return the configured JadeConfiguration object
   */
  public @Bean JadeConfiguration jadeConfiguration() {
    JadeConfiguration configuration = new JadeConfiguration();
    configuration.setCaching(false);
    configuration.setPrettyPrint(true);
    configuration.setTemplateLoader(templateLoader());
    return configuration;
  }

  /**
   * Instantiate and configure the JadeViewResolver for templating
   * 
   * @return a ViewResolver object containing the configured JadeViewResolver
   */
  public @Bean ViewResolver viewResolver() {
    JadeViewResolver viewResolver = new JadeViewResolver();
    viewResolver.setConfiguration(jadeConfiguration());
    return viewResolver;
  }

}
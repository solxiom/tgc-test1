/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.social;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 *
 * @author soleikav
 */
@Configuration
@PropertySource("classpath:com/solxiom/social/k23.properties")
public class PropertyPlaceholderConfig {
 
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
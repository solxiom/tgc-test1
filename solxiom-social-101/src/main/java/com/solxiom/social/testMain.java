package com.solxiom.social;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author soleikav
 */
//@PropertySource("classpath:org/springframework/social/showcase/config/application.properties")
@Configuration
@PropertySource("classpath:org/springframework/social/showcase/k23.properties")
public class testMain {

    @Autowired
    private Environment environment;
    @Value("${solxiom.userid}")
    private String userid;

    public testMain() throws Exception {
        Resource resource = new ClassPathResource("org/springframework/social/showcase/k23.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        userid = props.getProperty("solxiom.userid");
    }

    public String getUserId() {
//        return environment.getProperty("solxiom.userid");
        return this.userid;
    }

    public static void main(String[] args) throws Exception {

        System.out.println("yes..: " + new testMain().getUserId());
    }
}

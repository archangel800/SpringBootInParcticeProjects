package com.dzidziguri.springapplication.propertysourcespringapplication;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.context.ConfigurableApplicationContext;

import org.slf4j.Logger;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class PropertySourceSpringApplication {

	private static final Logger log = LoggerFactory.getLogger(PropertySourceSpringApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(PropertySourceSpringApplication.class);
		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
		Environment env = applicationContext.getBean(Environment.class);
		System.out.println("Database username: " + env.getProperty("spring.datasource.username"));
		System.out.println("Database password: " + env.getProperty("spring.datasource.password"));
		System.out.println();
		AppProperties appProperties = applicationContext.getBean(AppService.class).getAppProperties();
		System.out.println(appProperties);
		log.info(dbConfiguration.toString());
	}

}

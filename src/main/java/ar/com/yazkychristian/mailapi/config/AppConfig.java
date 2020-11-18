package ar.com.yazkychristian.mailapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import ar.com.yazkychristian.mailapi.config.AppConfig;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter @Setter
public class AppConfig {
	
	@Value("${freemarker.templates.path}")
	private String freeMarkerTemplatePath;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	    PropertySourcesPlaceholderConfigurer properties = 
	      new PropertySourcesPlaceholderConfigurer();
	    properties.setLocation(new FileSystemResource("/etc/encodemmerce/mail-api/application.properties"));
	    properties.setIgnoreResourceNotFound(false);
	    return properties;
	}	
}

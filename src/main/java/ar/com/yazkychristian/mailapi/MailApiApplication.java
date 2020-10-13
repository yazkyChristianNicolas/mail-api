package ar.com.yazkychristian.mailapi;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@SpringBootApplication
public class MailApiApplication {
	
	@Value("${freemarker.templates.path}")
	private String freeMarkerTemplatePath;

	public static void main(String[] args) {
		SpringApplication.run(MailApiApplication.class, args);
	}
	
	@Bean
	public Configuration freeMarkerConfig() throws IOException{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
		cfg.setDirectoryForTemplateLoading(new File(freeMarkerTemplatePath));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		cfg.setFallbackOnNullLoopVariable(false);
		return cfg;
	}

}

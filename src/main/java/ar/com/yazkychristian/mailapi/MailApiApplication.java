package ar.com.yazkychristian.mailapi;


import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@SpringBootApplication
@ComponentScan({"ar.com.yazkychristian.*","com.yazkychristian.*"})
public class MailApiApplication {
	
	Logger logger = LoggerFactory.getLogger(MailApiApplication.class);
	
	@Value("${freemarker.templates.path}")
	private String freeMarkerTemplatePath;

	public static void main(String[] args) {
		SpringApplication.run(MailApiApplication.class, args);
	}

	
	@Bean
	public Configuration freeMarkerConfig() throws IOException{
		logger.info("Freemarker templates directory: " + freeMarkerTemplatePath);
		Configuration cfg = (Configuration) new Configuration(freemarker.template.Configuration.VERSION_2_3_29);
		cfg.setDirectoryForTemplateLoading( new File(freeMarkerTemplatePath));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		cfg.setFallbackOnNullLoopVariable(false);
		return cfg;
	}
	
}

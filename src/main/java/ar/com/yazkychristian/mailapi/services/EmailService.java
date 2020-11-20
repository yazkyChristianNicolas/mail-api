package ar.com.yazkychristian.mailapi.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ar.com.yazkychristian.mailapi.beans.SendMailRequest;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


@Service
public class EmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration freeMarkerConfig;
	
	public void sendMail(SendMailRequest mailRequest) throws Exception{
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);	
		String htmlMessage = null;
		try {
			Template temp = freeMarkerConfig.getTemplate(mailRequest.getTemplate());
			htmlMessage = FreeMarkerTemplateUtils.processTemplateIntoString(temp, mailRequest.getData());
			
			helper.setTo(mailRequest.getTo());
			helper.setText(htmlMessage, true);
			helper.setSubject(mailRequest.getSubject());
			helper.setFrom(mailRequest.getFrom());
			sender.send(message);
			
		} catch (TemplateNotFoundException e) {
			logger.error("Template not found", e);
			throw e;
		} catch(MalformedTemplateNameException mte) {
			logger.error("MalformedTemplateNameException", mte);
			throw mte;
		} catch (MessagingException me) {
			logger.error("Hubo un error al enviar el mail: {}", me);
			throw me;
		}		
		
	}
}

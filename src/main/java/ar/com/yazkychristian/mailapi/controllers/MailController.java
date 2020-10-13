package ar.com.yazkychristian.mailapi.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ar.com.yazkychristian.mailapi.beans.SendMailRequest;
import ar.com.yazkychristian.mailapi.services.EmailService;

@RestController
@RequestMapping("mail")
public class MailController {
	
	@Autowired
	private EmailService emailService;
	
    private static final Logger logger = LogManager.getLogger(MailController.class.getName());
	
	@PostMapping()
	public ResponseEntity<?> sendMail(@Valid @RequestBody SendMailRequest mailRequest){
		logger.info("Send Email");
		logger.info(mailRequest.toString());
		
		return ResponseEntity.ok(mailRequest);
	}
}

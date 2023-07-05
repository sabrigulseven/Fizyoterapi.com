package com.sabrigulseven.appointment.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sabrigulseven.appointment.service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private final JavaMailSender mailSender;

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

	@Override
	public void sendTreatmentMail(String email, String pathName) {
		try {
			String subject = "Fizyoterapi.com Tedavi";
			String text = "Uygulamanız gereken tedavi dosyası ektedir.";
			String filePath = "pdf-files/" + pathName;
			FileSystemResource fileResource = new FileSystemResource(new File(filePath));
			String fileName = "Tedavi.pdf";

			sendMail(email, subject, text, fileResource, fileName);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private void sendMail(String to, String subject, String text, FileSystemResource fileResource, String fileName)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		helper.addAttachment(fileName, fileResource);

		mailSender.send(message);
	}
}

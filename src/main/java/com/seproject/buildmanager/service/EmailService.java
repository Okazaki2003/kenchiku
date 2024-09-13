package com.seproject.buildmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
  @Autowired
  private JavaMailSender mailSender;

  public void sendSimpleEmail(String toEmail, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject(subject);
    message.setText(body);
    message.setFrom("your_email@example.com");

    mailSender.send(message);
  }

  public void sendHtmlEmail(String toEmail, String subject, String htmlBody, File attachment)
      throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

    helper.setTo(toEmail);
    helper.setSubject(subject);
    helper.setText(htmlBody, true);
    helper.setFrom("your_email@example.com");

    if (attachment != null) {
      helper.addAttachment(attachment.getName(), attachment);
    }

    mailSender.send(mimeMessage);
  }
}

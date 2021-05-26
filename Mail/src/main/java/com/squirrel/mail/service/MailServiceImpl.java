package com.squirrel.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailServiceImpl implements MailService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(content);

        try {
            javaMailSender.send(simpleMailMessage);
            LOGGER.info("Mail send");
        } catch (MailException e) {
            LOGGER.error("Mail error " + e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);

            javaMailSender.send(mimeMessage);
            LOGGER.info("Html Mail send");
        } catch (MessagingException e) {
            LOGGER.error("Html Mail error " + e);
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = fileSystemResource.getFilename();
            mimeMessageHelper.addAttachment(fileName, fileSystemResource);

            javaMailSender.send(mimeMessage);
            LOGGER.info("Attachment Mail send");
        } catch (MessagingException e) {
            LOGGER.error("Attachment Mail error " + e);
        }
    }

    @Override
    public void sendInlineResourceMail(
            String to, String subject, String content, String resourcePath, String resourceId) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(resourcePath));
            mimeMessageHelper.addInline(resourceId, fileSystemResource);

            javaMailSender.send(mimeMessage);
            LOGGER.info("InlineResource Mail send");
        } catch (MessagingException e) {
            LOGGER.error("InlineResource Mail error " + e);
        }
    }
}

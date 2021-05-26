package com.squirrel.mail.web;

import com.squirrel.mail.result.MailResult;
import com.squirrel.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MailController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MailService mailService;

    @RequestMapping("/sendSimpleMail")
    public MailResult sendSimpleMail(String to, String subject, String content) {
        MailResult mailResult = new MailResult();
        if (ObjectUtils.isEmpty(to) || !to.contains("@")) {
            mailResult.setRespondCode("01");
            mailResult.setRespondMessage("mail address not valid");
        }
        if (ObjectUtils.isEmpty(content)) {
            mailResult.setRespondCode("03");
            mailResult.setRespondMessage("content can not be null");
        }
        try {
            mailService.sendSimpleMail(to, subject, content);
            LOGGER.info("Simple Mail send");
        } catch (MailException e) {
            mailResult.setRespondCode("04");
            mailResult.setRespondMessage("Send error");
            LOGGER.error("SimpleMail Error " + e);
        }
        return mailResult;
    }

}

package com.squirrel.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail("631253076@qq.com", "TestSimpleMail", "Test");
    }

    @Test
    public void testHtmlMail() {
        String content = "<html>\n <body>\n <h3> html mail </h3>\n </body>\n </html>";
        mailService.sendHtmlMail("631253076@qq.com", "TestHtmlMail", content);
    }

    @Test
    public void testAttachmentsMail() {
        String filePath = "/Users/zhiyuanma/Downloads/4643.jpeg";
        mailService.sendAttachmentsMail(
                "631253076@qq.com", "TestAttachmentsMail", "content", filePath);
    }

    @Test
    public void testInlineResourceMail() {
        String imgPath = "/Users/zhiyuanma/Downloads/4643.jpeg";
        String resourceId = "123";
        String content =
                "<html> <body>Picture Mail<img src=\'cid:" + resourceId + "\'></body></html>";
        mailService.sendInlineResourceMail(
                "631253076@qq.com", "Picture Mail", content, imgPath, resourceId);
    }

    @Test
    public void sendTemplateMail() {
        Context context = new Context();
        context.setVariable("id", "1");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("631253076@qq.com", "Template Mail", emailContent);
    }


}

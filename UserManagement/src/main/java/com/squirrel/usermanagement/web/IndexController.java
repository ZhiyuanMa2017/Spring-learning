package com.squirrel.usermanagement.web;

import com.squirrel.usermanagement.config.WebConfiguration;
import com.squirrel.usermanagement.model.User;
import com.squirrel.usermanagement.param.LoginParam;
import com.squirrel.usermanagement.param.RegisterParam;
import com.squirrel.usermanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username")
    private String from;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute(WebConfiguration.LOGIN_KEY);
        if (ObjectUtils.isEmpty(id)) {
            return "login";
        } else {
            return "redirect:/list";
        }
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(@Valid LoginParam loginParam, BindingResult result, ModelMap model, HttpServletRequest request) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg += error.getCode() + "-" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }
        User user = userRepository.findByUserName(loginParam.getLoginName());
        if (user == null) {
            user = userRepository.findByEmail(loginParam.getLoginName());
        }
        if (user == null) {
            model.addAttribute("errorMsg", "User not exists");
            return "login";
        } else if (!user.getPassWord().equals(loginParam.getPassWord())) {
            model.addAttribute("errorMsg", "Wrong password");
            return "login";
        }
        request.getSession().setAttribute(WebConfiguration.LOGIN_KEY, user.getId());
        request.getSession().setAttribute(WebConfiguration.LOGIN_USER, user);
        return "redirect:/list";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(WebConfiguration.LOGIN_USER);
        request.getSession().removeAttribute(WebConfiguration.LOGIN_KEY);
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("register")
    public String register(@Valid RegisterParam registerParam, BindingResult result, ModelMap model) {
        LOGGER.info("register param" + registerParam.toString());
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg += error.getCode() + "-" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "register";
        }
        User u = userRepository.findByUserNameOrEmail(registerParam.getUserName(), registerParam.getEmail());
        if (u != null) {
            model.addAttribute("errorMsg", "User exists");
            return "register";
        }
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        user.setRegTime(new Date());
        user.setUserType("manage");
        user.setState("unverified");
        userRepository.save(user);
        sendRegisterMail(user);
        LOGGER.info("register user " + user.toString());
        return "login";
    }

    public void sendRegisterMail(User user) {
        Context context = new Context();
        context.setVariable("id", user.getId());
        String emailContent = templateEngine.process("emailTemplate", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(user.getEmail());
            helper.setSubject("Registration verify email");
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("Email error", e);
        }
    }

    @RequestMapping("/verified/{id}")
    public String verified(@PathVariable("id") String id, ModelMap model) {
        User user = userRepository.findById(id).get();
        if (user != null && "unverified".equals(user.getState())) {
            user.setState("verified");
            userRepository.save(user);
            model.put("userName", user.getUserName());
        }
        return "verified";
    }
}

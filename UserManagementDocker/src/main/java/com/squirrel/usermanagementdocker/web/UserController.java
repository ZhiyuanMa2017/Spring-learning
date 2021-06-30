package com.squirrel.usermanagementdocker.web;

import com.squirrel.usermanagementdocker.model.User;
import com.squirrel.usermanagementdocker.param.UserParam;
import com.squirrel.usermanagementdocker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("list")
    @Cacheable(value = "user_list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> users = userRepository.findAll(pageable);
        model.addAttribute("users", users);
        LOGGER.info("user list " + users.getContent());
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(@Valid UserParam userParam, BindingResult result, ModelMap model) {
        String errMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errMsg += error.getCode() + "-" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errMsg);
            return "user/userAdd";
        }
        User user = userRepository.findByUserNameOrEmail(
                userParam.getUserName(), userParam.getEmail());
        if (user != null) {
            model.addAttribute("errorMsg", "User already exists");
            return "user/userAdd";
        }
        User user1 = new User();
        BeanUtils.copyProperties(userParam, user1);
        user1.setRegTime(new Date());
        user1.setUserType("user");
        userRepository.save(user1);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, String id) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam, BindingResult result, ModelMap model) {
        String errMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errMsg += error.getCode() + "-" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errMsg);
            model.addAttribute("user", userParam);
            return "user/userEdit";
        }
        User user = userRepository.findById(userParam.getId()).get();

        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        userRepository.deleteById(id);
        return "redirect:/list";
    }

}

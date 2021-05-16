package com.squirrel.jpathymeleaf.web;

import com.squirrel.jpathymeleaf.model.MyUser;
import com.squirrel.jpathymeleaf.param.UserParam;
import com.squirrel.jpathymeleaf.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<MyUser> users = userRepository.findList(pageable);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(@Valid UserParam userParam, BindingResult result, Model model) {
        StringBuilder errorMsg = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg.append(
                        error.getCode()).append("-").append(error.getDefaultMessage()).append(";");
            }
            model.addAttribute("errorMsg", errorMsg.toString());
            return "user/userAdd";
        }

        MyUser u = userRepository.findByUserName(userParam.getUserName());
        if (u != null) {
            model.addAttribute("errorMsg", "User exists!");
            return "user/userAdd";
        }
        MyUser myUser = new MyUser();
        BeanUtils.copyProperties(userParam, myUser);
        myUser.setRegTime(new Date());
        userRepository.save(myUser);
        return "redirect:/list";
    }


    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        MyUser myUser = userRepository.findById((long) id);
        model.addAttribute("user", myUser);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam, BindingResult result, Model model) {
        StringBuilder errorMsg = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg.append(
                        error.getCode()).append("-").append(error.getDefaultMessage()).append(";");
            }
            model.addAttribute("errorMsg", errorMsg.toString());
            model.addAttribute("user", userParam);
            return "user/userEdit";
        }
        MyUser myUser = new MyUser();
        BeanUtils.copyProperties(userParam, myUser);
        myUser.setRegTime(new Date());
        userRepository.save(myUser);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "redirect:/list";
    }
}

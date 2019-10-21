package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FormController {

    @Autowired
    private UserService userService;



    @GetMapping("/")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("information", new User());
        return modelAndView;
    }

//    @PostMapping("/")
//    public String checkValidation(@Valid @ModelAttribute("information") User user, BindingResult bindingResult, Model model){
//        new User().validate(user,bindingResult);
//        if (bindingResult.hasFieldErrors()){
//            return "index";
//        }
//        else {
//            model.addAttribute("phoneNumber",user.getPhoneNumber());
//            return "result";
//        }
//    }

    @PostMapping("/save-user")
    public ModelAndView saveUser(@Valid @ModelAttribute("information") User user,BindingResult bindingResult) {
        new User().validate(user,bindingResult);

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()){
            System.out.println(">> add model error to mvc");
            modelAndView.setViewName("index");
            modelAndView.addObject("information", user);
            modelAndView.addAllObjects(bindingResult.getModel());
            return modelAndView;
        }
        System.out.println(">>> controller save-user");
        userService.save(user);
        modelAndView.addObject("information", new User());
        modelAndView.setViewName("result");
        return modelAndView;
    }


}

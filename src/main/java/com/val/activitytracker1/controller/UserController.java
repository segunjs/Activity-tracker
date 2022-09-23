package com.val.activitytracker1.controller;


import com.val.activitytracker1.model.User;
import com.val.activitytracker1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value ="/login")
    String login(@RequestParam("email") String email, @RequestParam("password") String pass, HttpSession model, Model model1){
        User user;
        user =userService.getUser(email, pass);
        if(user != null){
            model.setAttribute("user", user);
            user.setUsername(user.getUsername().split(" ")[0]);

            return "index";
        }
        else{
            String message = "username or password is incorrect";
            model1.addAttribute("errorMessage", message);
            return "login";
        }
    }

    @PostMapping(value = "/userSignUp")
    String userSignUp(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password,
                      @RequestParam("passConfirm") String passConfirm, HttpSession session){
        User user = new User(name, email, "U", password);
        if(password.equals(passConfirm)){
            if(userService.validEmail(email) == 0){
                userService.adduser(user);
                user = userService.getUser(email, password);
                user.setUsername(user.getUsername().split(" ")[0]);
                session.setAttribute("user", user);
                return "login";
            }else{
                return "signUp ";
            }
        }else {
            return "signUp ";
        }
    }

    @PostMapping(value ="/inauguration")
    String adminSignUp(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password,
                       @RequestParam("passConfirm") String passConfirm, HttpSession session) {
        User user = new User(name, email, password, "A");
        if (password.equals(passConfirm)) {
            if (userService.validEmail(email) == 0) {
                userService.adduser(user);
                user = userService.getUser(email, password);
                session.setAttribute("user", user);
                return "redirect:/theSeat";
            } else {
                return "Email already in use ";
            }
        } else {
            return "Passwords didn't match ";
        }
    }

    @GetMapping(value = "/logout")
    String userLogout(HttpSession session){
        session.setAttribute("user", null);
        return "login";
    }
    @GetMapping(value = "/signUp")
    String userSignUp(HttpSession session){
        return "signUp";
    }
}


package com.axis.minagri.security.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tahar on 04/11/2016.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping("/register")
    public String register(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "register";
    }

    @RequestMapping("/successRegister")
    public String successRegister(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "successRegister";
    }

    @RequestMapping("/logout")
    public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "logout";
    }


   /* @RequestMapping("/invalidSession")
    public String invalidSession(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "invalidSession";
    }*/
}

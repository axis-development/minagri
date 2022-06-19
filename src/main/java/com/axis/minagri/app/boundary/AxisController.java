package com.axis.minagri.app.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AxisController {

    @RequestMapping("/")
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @RequestMapping("/home")
    public String home(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @RequestMapping("/customer/customerView")
    public String customer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "customer/customerView";
    }

    @RequestMapping("/customer/customerAdd")
    public String addCustomer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "customer/customerAdd";
    }

    @RequestMapping("/company/bizprofile")
    public String bizprofile(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return"company/bizprofile";
    }

    @RequestMapping("/quote/quoteView")
    public String quote(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "quote/quoteView";
    }

    @RequestMapping(value = "/quote/add/{customerId}")
    public String addQuoteCustomerId(HttpServletRequest request, @PathVariable String customerId) {
        request.getSession().setAttribute("customerId", customerId);
        return "quote/quoteAdd";
    }
}

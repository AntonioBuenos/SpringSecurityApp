package by.smirnov.springsecurityapp.controllers;

import by.smirnov.springsecurityapp.security.PersonDetails;
import by.smirnov.springsecurityapp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private final AdminService adminService;

    @Autowired
    public TestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getUsername();
    }

    @GetMapping("/admin")
    public String adminPage(){
        adminService.doingAdminStuff();
        return "admin";
    }
}

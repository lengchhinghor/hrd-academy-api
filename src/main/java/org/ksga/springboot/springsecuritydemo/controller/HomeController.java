package org.ksga.springboot.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("dashboard")
    public String accessDashbard() {
        return "dashboard";
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String onlyRoleAdmin() {
        return "admin";
    }

}
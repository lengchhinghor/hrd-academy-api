package org.ksga.springboot.springsecuritydemo.controller.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestRestController {
    //    private String defaultRolePrefix = "ROLE_";
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    @GetMapping("/user")
    public String userAccess() {
        return "User Content.";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/dashboard")
    public String dashboardAccess() {
        return "Dashboard";
    }
}

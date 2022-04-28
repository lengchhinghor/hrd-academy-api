package org.ksga.springboot.springsecuritydemo.payload.request;

import java.util.Set;

public class UserRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Set<String> roles;
}

package org.ksga.springboot.springsecuritydemo.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class RegisterRequest {
    private String username;
    private String password;
    private Set<String> roles;
}

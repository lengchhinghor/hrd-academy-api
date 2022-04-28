package org.ksga.springboot.springsecuritydemo.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LoginRequest {
    private String username;
    private String password;
}

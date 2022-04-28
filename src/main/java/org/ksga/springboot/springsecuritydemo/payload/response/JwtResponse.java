package org.ksga.springboot.springsecuritydemo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Accessors(chain = true)
public class JwtResponse {
    private String token;
    private Long id;
    private String username;
}

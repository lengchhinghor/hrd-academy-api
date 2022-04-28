package org.ksga.springboot.springsecuritydemo.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Role {
    private Long id;
    private ERole name;
}

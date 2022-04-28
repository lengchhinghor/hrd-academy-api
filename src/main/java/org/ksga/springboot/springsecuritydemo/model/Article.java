package org.ksga.springboot.springsecuritydemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Article {
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
}

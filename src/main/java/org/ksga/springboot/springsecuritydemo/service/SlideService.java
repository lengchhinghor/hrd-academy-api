package org.ksga.springboot.springsecuritydemo.service;

import org.ksga.springboot.springsecuritydemo.model.slide.Slide;

import java.util.List;

public interface SlideService {
    public List<Slide> findAll();
}

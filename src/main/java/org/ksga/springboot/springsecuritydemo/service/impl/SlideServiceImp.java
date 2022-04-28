package org.ksga.springboot.springsecuritydemo.service.impl;

import org.ksga.springboot.springsecuritydemo.model.slide.Slide;
import org.ksga.springboot.springsecuritydemo.payload.mapper.SlideMapper;
import org.ksga.springboot.springsecuritydemo.repository.SlideRepository;
import org.ksga.springboot.springsecuritydemo.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.modelmapper.ModelMapper;


@Service
public class SlideServiceImp implements SlideService {
private SlideRepository repository;
private SlideMapper slideMapper;
ModelMapper mapper = new ModelMapper();


    @Autowired
    public SlideServiceImp(SlideRepository repository) {
        this.repository = repository;
        this.slideMapper = slideMapper;
    }

    @Override
    public List<Slide> findAll() {
        return repository.findAll();
    }
}

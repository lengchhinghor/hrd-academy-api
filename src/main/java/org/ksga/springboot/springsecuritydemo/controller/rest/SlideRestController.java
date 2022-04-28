package org.ksga.springboot.springsecuritydemo.controller.rest;

import org.ksga.springboot.springsecuritydemo.payload.dto.SlideDto;
import org.ksga.springboot.springsecuritydemo.payload.response.Response;
import org.ksga.springboot.springsecuritydemo.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/slide")
public class SlideRestController {
    private final SlideService slideService;
    @Autowired

    public SlideRestController(SlideService slideService) {
        this.slideService = slideService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public Response<SlideDto> getSlide(){
        SlideDto slideDto = (SlideDto) slideService.findAll();
        return Response
                .<SlideDto>ok()
                .setPayload(slideDto);
    }
}

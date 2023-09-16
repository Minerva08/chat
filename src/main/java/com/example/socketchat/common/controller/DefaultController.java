package com.example.socketchat.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class DefaultController {
    @GetMapping("/service-error")
    public ModelAndView error(){
        log.error("service Error");
        return new ModelAndView("e-500");
    }
}

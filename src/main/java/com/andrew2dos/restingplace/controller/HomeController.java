package com.andrew2dos.restingplace.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
@AllArgsConstructor
public class HomeController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @ExceptionHandler(Exception.class)
    public String handle(Exception e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("errorMessage", "ERROR. " + e.getMessage());
        return "error";
    }
}

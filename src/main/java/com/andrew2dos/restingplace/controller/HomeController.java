package com.andrew2dos.restingplace.controller;

import com.andrew2dos.restingplace.dto.BookingDto;
import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.facade.UserFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class HomeController {


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Authentication authentication, Model model) {
        if(authentication != null) {
            String userName = authentication.getName();
            model.addAttribute("userName", userName);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("errorMessage", "ERROR. " + e.getMessage());
        return "error";
    }
}

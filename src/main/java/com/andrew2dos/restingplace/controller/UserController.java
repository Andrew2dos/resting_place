package com.andrew2dos.restingplace.controller;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.facade.UserFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @RequestMapping(value = {"/userPage"}, method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "userPage";
    }

    @RequestMapping(value = {"/userPage"}, method = RequestMethod.POST)
    public String userPage(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userPage";
        }
        model.addAttribute("chosenPlace", userDto.toString());
        userFacade.save(userDto);
        return "success";
    }

}

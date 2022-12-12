package com.andrew2dos.restingplace.controller;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.Role;
import com.andrew2dos.restingplace.enums.RoleName;
import com.andrew2dos.restingplace.facade.UserFacade;
import com.andrew2dos.restingplace.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(String userName, String password, String email){
        ModelAndView mv = new ModelAndView();
        if(StringUtils.isBlank(userName)){
            mv.setViewName("/register");
            mv.addObject("error", "name cannot be empty");
            return mv;
        }
        if(StringUtils.isBlank(password)){
            mv.setViewName("/register");
            mv.addObject("error", "password cannot be empty");
            return mv;
        }
        if(userFacade.existsByUserName(userName)){
            mv.setViewName("/register");
            mv.addObject("error", "that username already exists");
            return mv;
        }
        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        userDto.setPassword(passwordEncoder.encode(password));
        userDto.setEmail(email);
        Role roleUser = roleService.getByRoleName(RoleName.ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        userDto.setRoles(roles);
        userFacade.save(userDto);
        mv.setViewName("/login");
        mv.addObject("registerOK", "Account created, " + userDto.getUserName() + ", you can now log in");
        return mv;
    }

//    @RequestMapping(value = {"/userPage"}, method = RequestMethod.GET)
//    public String userPage(Model model) {
//        model.addAttribute("userDto", new UserDto());
//        return "userPage";
//    }
//
//    @RequestMapping(value = {"/userPage"}, method = RequestMethod.POST)
//    public String userPage(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "userPage";
//        }
//        model.addAttribute("chosenPlace", userDto.toString());
//        userFacade.save(userDto);
//        return "success";
//    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("errorMessage", "ERROR. " + e.getMessage());
        return "error";
    }

}

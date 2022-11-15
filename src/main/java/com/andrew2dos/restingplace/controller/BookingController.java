package com.andrew2dos.restingplace.controller;

import com.andrew2dos.restingplace.dto.BookingDto;
import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.facade.BookingFacade;
import com.andrew2dos.restingplace.facade.UserFacade;
import com.andrew2dos.restingplace.service.impl.PlaceServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class BookingController {

    private final PlaceServiceImpl placeService;
    private final UserFacade userFacade;
    private final BookingFacade bookingFacade;


    @RequestMapping(value = {"/bookingList"}, method = RequestMethod.GET)
    public String bookingList(Model model) {
        List<BookingDto> bookingDtos = bookingFacade.getAll();
        model.addAttribute("bookings", bookingDtos);

        return "bookingList";
    }

    @RequestMapping(value = {"/bookingPage"}, method = RequestMethod.GET)
    public String bookingPage(Model model) {

        List<Place> allPlaces = placeService.getAllPlaces();
        List<UserDto> allUsers = userFacade.getAllUsers();
        model.addAttribute("bookingDTO", new BookingDto());
        model.addAttribute("places", allPlaces);
        model.addAttribute("users", allUsers);

        return "bookingPage";
    }

    @RequestMapping(value = {"/bookingPage", "/update"}, method = RequestMethod.POST)
    public String bookingPage(@Valid @ModelAttribute("bookingDTO") BookingDto bookingDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bookingPage";
        }
        model.addAttribute("chosenPlace", bookingDto.toString());
        bookingFacade.save(bookingDto);
        return "success";
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
    public String update(@Valid @ModelAttribute("id") Long id, Model model) {

        BookingDto bookingDTO = bookingFacade.findById(id);
        List<Place> allPlaces = placeService.getAllPlaces(); // 2 раза
        List<UserDto> allUsers = userFacade.getAllUsers();    // 2 раза
        model.addAttribute("bookingDTO", bookingDTO);
        model.addAttribute("places", allPlaces);
        model.addAttribute("users", allUsers);
        return "bookingPage";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
    public String delete(@Valid @ModelAttribute("id") Long id) {
        bookingFacade.deleteById(id);
        return "redirect:/bookingList";
    }

}

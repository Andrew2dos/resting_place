package com.andrew2dos.restingplace.controller;

import com.andrew2dos.restingplace.dto.BookingDto;
import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.Booking;
import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.facade.BookingFacade;
import com.andrew2dos.restingplace.facade.UserFacade;
import com.andrew2dos.restingplace.security.service.MainUser;
import com.andrew2dos.restingplace.service.impl.PlaceServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class BookingController {

    private final PlaceServiceImpl placeService;
    private final UserFacade userFacade;
    private final BookingFacade bookingFacade;

    public BookingController(PlaceServiceImpl placeService, UserFacade userFacade, BookingFacade bookingFacade) {
        this.placeService = placeService;
        this.userFacade = userFacade;
        this.bookingFacade = bookingFacade;
    }

    public UserDto getAuthenticatedUserDto(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userFacade.getByUserName(authentication.getName());
    }

    @RequestMapping(value = {"/bookingList"}, method = RequestMethod.GET)
    public String bookingList(@AuthenticationPrincipal MainUser authentication, Model model) {
        List<BookingDto> bookings = bookingFacade.getAll();

        if(authentication != null) {
            UserDto userDto = getAuthenticatedUserDto();
            List<BookingDto> bookingAuthorized = bookingFacade.getNonAuthorizedUsers();
            List<BookingDto> userBookings = bookingFacade.findByUserId(userDto.getId());
            model.addAttribute("userBookings", userBookings);
            model.addAttribute("bookings", bookingAuthorized);
        } else {
            model.addAttribute("bookings", bookings);
        }
        return "bookingList";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/bookingPage"}, method = RequestMethod.GET)
    public String bookingPage(Model model) {

        List<Place> allPlaces = placeService.getAllPlaces();
        model.addAttribute("bookingDTO", new BookingDto());
        model.addAttribute("places", allPlaces);
        model.addAttribute("userDto", getAuthenticatedUserDto().getUserName());

        return "bookingPage";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/bookingPage", "/update"}, method = RequestMethod.POST)
    public String bookingPage(@Valid @ModelAttribute("bookingDTO") BookingDto bookingDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bookingPage";
        }
        model.addAttribute("chosenPlace", bookingDto.toString());
        bookingFacade.save(bookingDto);
        return "success";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
    public String update(@Valid @ModelAttribute("id") Long id, Model model) {

        BookingDto bookingDTO = bookingFacade.findById(id);
        fillPlacesData(model);
        List<UserDto> allUsers = userFacade.getAllUsers();    // 2 раза
        model.addAttribute("users", allUsers);
        model.addAttribute("bookingDTO", bookingDTO);
        model.addAttribute("userDto", getAuthenticatedUserDto());
        return "bookingPage";
    }

    private void fillPlacesData(Model model) {
        List<Place> allPlaces = placeService.getAllPlaces(); // 2 раза
        model.addAttribute("places", allPlaces);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
    public String delete(@Valid @ModelAttribute("id") Long id) {
        bookingFacade.deleteById(id);
        return "redirect:/bookingList";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("errorMessage", "ERROR. " + e.getMessage());
        return "error";
    }

}

package com.andrew2dos.restingplace.controller;

import com.andrew2dos.restingplace.entity.Camper;
import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.service.CamperServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class CamperController {

    private final CamperServiceImpl camperService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
        public String index() {
        return "index";
    }

    @RequestMapping(value = {"/bookingList"}, method = RequestMethod.GET)
    public String bookingList(Model model) {
        List<Camper> bookingList = camperService.getAllCampers();
        model.addAttribute("bookingList", bookingList);

        return "bookingList";
    }

    @RequestMapping(value = {"/userPage"}, method = RequestMethod.GET)
    public String userPage(Model model) {
        List<Place> allPlaces = camperService.getAllPlaces();
        model.addAttribute("camper", new Camper());
        model.addAttribute("places", allPlaces);

        return "userPage";
    }

    @RequestMapping(value = {"/userPage", "/update"}, method = RequestMethod.POST)
    public String userPage(@ModelAttribute("camper") Camper camper, Model model) {
        model.addAttribute("chosenPlace", camper.toString());

        camperService.saveCamper(camper);
        return "success";
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.GET)
    public String update(@Valid @ModelAttribute("camperId") Long bookingId, Model model) {

        List<Place> allPlaces = camperService.getAllPlaces(); // 2 раза
        Camper camper = camperService.findById(bookingId);
        model.addAttribute("camper", camper);
        model.addAttribute("places", allPlaces); // 2 раза

        return "userPage";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
    public String delete(@Valid @ModelAttribute("camperId") Long bookingId) {
        camperService.deleteById(bookingId);
        return "redirect:/bookingList";
    }
}

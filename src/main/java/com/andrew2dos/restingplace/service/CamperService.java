package com.andrew2dos.restingplace.service;

import com.andrew2dos.restingplace.entity.Camper;
import com.andrew2dos.restingplace.entity.Place;

import java.util.List;

public interface CamperService {
    List<Place> getAllPlaces();
    List<Camper> getAllCampers();
    void deleteById(Long bookingId);
    Camper findById(Long bookingId);
}

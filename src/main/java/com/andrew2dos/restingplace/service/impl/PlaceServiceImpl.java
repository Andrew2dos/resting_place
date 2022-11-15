package com.andrew2dos.restingplace.service.impl;

import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.repository.PlaceRepository;
import com.andrew2dos.restingplace.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}

package com.andrew2dos.restingplace.service;

import com.andrew2dos.restingplace.entity.Camper;
import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.repository.CamperRepository;
import com.andrew2dos.restingplace.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CamperServiceImpl implements CamperService {

    private final PlaceRepository placeRepository;
    private final CamperRepository camperRepository;

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public List<Camper> getAllCampers() {
        return camperRepository.findAll();
    }

    @Override
    public void deleteById(Long bookingId) {
        camperRepository.deleteById(bookingId);
    }

    @Override
    public Camper findById(Long bookingId) {
        Optional<Camper> result = camperRepository.findById(bookingId);

        Camper theCamper = null;

        if (result.isPresent()) {
            theCamper = result.get();
        } else {
            throw new RuntimeException("Did not find booking id - " + bookingId);
        }
        return theCamper;
    }

    public Camper saveCamper(Camper camper) {
        return camperRepository.save(camper);
    }
}

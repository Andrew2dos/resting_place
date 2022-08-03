package com.andrew2dos.restingplace.service;

import com.andrew2dos.restingplace.entity.Camper;
import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.repository.CamperRepository;
import com.andrew2dos.restingplace.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        return camperRepository.findAllByOrderByBookingId();
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
        if(isWithinRange(camper.getBookingId(), camper.getPlace(), camper.getStartDate(), camper.getEndDate())){
            throw new RuntimeException("The '" + camper.getPlace().getPlaceName() + "' is already booked for this date range");
        } else {
            return camperRepository.save(camper);
        }
    }

    public boolean isWithinRange(Long id, Place place, Date startDate, Date endDate){

        List<Camper> camperList = getAllCampers();

        boolean isOverlap = false;

        for (Camper camp : camperList ) {
            Date testDateStart = camp.getStartDate();
            Date testDateEnd = camp.getEndDate();
            Place testPlace = camp.getPlace();
            if(     (camp.getBookingId() != id)
                    && (testPlace.equals(place))
                    && (startDate.getTime() <= testDateEnd.getTime())
                    && (testDateStart.getTime() <= endDate.getTime())){
                isOverlap = true;
            }
        }
        return isOverlap;
    }
}

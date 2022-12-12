package com.andrew2dos.restingplace.service.impl;

import com.andrew2dos.restingplace.entity.Booking;
import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.repository.BookingRepository;
import com.andrew2dos.restingplace.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAllByOrderByIdAsc();
    }

    @Override
    public void deleteById(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<Booking> findByUserId(Long id) {
        return bookingRepository.findAllByUserId(id);
    }

    @Override
    public Booking findById(Long bookingId) {
        Optional<Booking> result = bookingRepository.findById(bookingId);

        Booking theBooking = null;

        if (result.isPresent()) {
            theBooking = result.get();
        } else {
            throw new RuntimeException("Did not find booking id - " + bookingId);
        }
        return theBooking;
    }

    @Override
    public Booking save(Booking booking) {
        if(isWithinRange(booking.getId(), booking.getPlace(), booking.getStartDate(), booking.getEndDate())){
            throw new RuntimeException("The '" + booking.getPlace().getName() + "' is already booked for this date range");
        } else {
            return bookingRepository.save(booking);
        }
    }

    private boolean isWithinRange(Long id, Place place, Date startDate, Date endDate){

        List<Booking> bookingList = getAllBooking();

        boolean isOverlap = false;

        for (Booking booking : bookingList ) {
            Date testDateStart = booking.getStartDate();
            Date testDateEnd = booking.getEndDate();
            Place testPlace = booking.getPlace();
            if(     (booking.getId() != id)
                    && (testPlace.equals(place))
                    && (startDate.getTime() <= testDateEnd.getTime())
                    && (testDateStart.getTime() <= endDate.getTime())){
                isOverlap = true;
            }
        }
        return isOverlap;
    }
}

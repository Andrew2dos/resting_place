package com.andrew2dos.restingplace.service;

import com.andrew2dos.restingplace.entity.Booking;
import com.andrew2dos.restingplace.entity.User;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBooking();
    Booking findById(Long bookingId);
    Booking save(Booking booking);
    void deleteById(Long bookingId);
    List<Booking> findByUserId(Long id);
}

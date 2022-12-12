package com.andrew2dos.restingplace.facade;

import com.andrew2dos.restingplace.dto.BookingDto;
import com.andrew2dos.restingplace.entity.Booking;

import java.util.List;

public interface BookingFacade {
    List<BookingDto> getAll();

    public List<BookingDto> getNonAuthorizedUsers();

    BookingDto findById(Long bookingId);

    Booking save(BookingDto booking);

    void deleteById(Long bookingId);

    public List<BookingDto> findByUserId(Long id);
}

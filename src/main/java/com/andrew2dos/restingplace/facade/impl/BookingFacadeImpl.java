package com.andrew2dos.restingplace.facade.impl;

import com.andrew2dos.restingplace.dto.BookingDto;
import com.andrew2dos.restingplace.entity.Booking;
import com.andrew2dos.restingplace.facade.BookingFacade;
import com.andrew2dos.restingplace.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingFacadeImpl implements BookingFacade {
    private final BookingService bookingService;
    private final ModelMapper modelMapper;

    public BookingFacadeImpl(BookingService bookingService, ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookingDto> getAll() {
        return bookingService.getAllBooking().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto findById(Long bookingId) {
        Booking booking = bookingService.findById(bookingId);
        BookingDto bookingDto = toDto(booking);
        return bookingDto;
    }

    @Override
    public Booking save(BookingDto bookingDto) {
        Booking booking = toModel(bookingDto);
        return bookingService.save(booking);
    }

    @Override
    public void deleteById(Long id) {
        bookingService.deleteById(id);
    }

    private Booking toModel(BookingDto bookingDto) {
        return modelMapper.map(bookingDto, Booking.class);
    }

    private BookingDto toDto(Booking booking) {
        BookingDto bookingDto = modelMapper.map(booking, BookingDto.class);
        return bookingDto;
    }
}

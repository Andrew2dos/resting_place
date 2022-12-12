package com.andrew2dos.restingplace.facade.impl;

import com.andrew2dos.restingplace.entity.User;
import com.andrew2dos.restingplace.mappers.BookingMapper;
import com.andrew2dos.restingplace.dto.BookingDto;
import com.andrew2dos.restingplace.entity.Booking;
import com.andrew2dos.restingplace.facade.BookingFacade;
import com.andrew2dos.restingplace.service.BookingService;
import com.andrew2dos.restingplace.service.impl.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingFacadeImpl implements BookingFacade {
    private final BookingService bookingService;

    private final UserServiceImpl userService;

    public BookingFacadeImpl(BookingService bookingService, UserServiceImpl userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    public User getAuthenticatedUserDto(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        Optional<User> optional = userService.getByUserName(userName);
        User user = optional.get();

        return user;
    }

    @Override
    public List<BookingDto> getAll() {
        return bookingService.getAllBooking().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getNonAuthorizedUsers() {
        return bookingService.getAllBooking()
                .stream()
                .map(this::toDto)
                .filter(bookingDto -> !bookingDto.getUser().getId().equals(getAuthenticatedUserDto().getId()))
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

    @Override
    public List<BookingDto> findByUserId(Long id) {
        return bookingService.findByUserId(id).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private Booking toModel(BookingDto bookingDto) {
        return BookingMapper.INSTANCE.toModel(bookingDto);
    }

    private BookingDto toDto(Booking booking) {
        return BookingMapper.INSTANCE.toDto(booking);
    }
}

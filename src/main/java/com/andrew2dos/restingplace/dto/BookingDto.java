package com.andrew2dos.restingplace.dto;

import com.andrew2dos.restingplace.entity.Place;
import com.andrew2dos.restingplace.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BookingDto {
    private Long id;

    private Place place; // PlaceDto

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date startDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date endDate;

    private User user; // UserDto

    @Override
    public String toString() {
        return "Booking{ " +
                "user=" + user.getUserName() +
                ", place=" + place.getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

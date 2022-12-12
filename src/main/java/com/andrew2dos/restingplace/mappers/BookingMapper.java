package com.andrew2dos.restingplace.mappers;

import com.andrew2dos.restingplace.dto.BookingDto;
import com.andrew2dos.restingplace.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking toModel(BookingDto bookingDto);
    BookingDto toDto(Booking booking);
}

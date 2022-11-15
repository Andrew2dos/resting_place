package com.andrew2dos.restingplace.repository;

import com.andrew2dos.restingplace.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByOrderByIdAsc();
}

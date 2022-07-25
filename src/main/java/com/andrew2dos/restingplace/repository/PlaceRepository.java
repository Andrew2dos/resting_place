package com.andrew2dos.restingplace.repository;

import com.andrew2dos.restingplace.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}

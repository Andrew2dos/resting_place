package com.andrew2dos.restingplace.repository;

import com.andrew2dos.restingplace.entity.Camper;
import com.andrew2dos.restingplace.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CamperRepository extends JpaRepository<Camper, Long> {

    List<Camper> findAll();
    //findAllByOrderByIdAsc();;
}

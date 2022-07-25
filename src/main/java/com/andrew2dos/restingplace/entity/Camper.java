package com.andrew2dos.restingplace.entity;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bookings", schema = "public", catalog = "resting_place")
public class Camper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long bookingId;

    @NotNull(message = "Name's cannot be empty.")
    @Column(name = "user_name")
    private String userName;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "start_date")
    private Date startDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "end_date")
    private Date endDate;

//    @JoinColumn("place_id")
//    private Place place;
//
//    @DateTimeFormat(pattern = "hh:mm")
//    private Date hourFrom;
//
//    @DateTimeFormat(pattern = "hh:mm")
//    private Date hourTo;


    @Override
    public String toString() {
        return "Camper: \n" +
                ", user - '" + userName + '\'' + "\n" +
                ", place - '" + place + '\'' + "\n" +
                ", start - " + startDate + "\n" +
                ", end - " + endDate;
    }
}

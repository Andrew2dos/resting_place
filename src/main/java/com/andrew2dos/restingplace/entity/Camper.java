package com.andrew2dos.restingplace.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "bookings", schema = "public", catalog = "resting_place")
public class Camper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long bookingId;


    @Column(name = "user_name")
    @NotEmpty(message = "Name's cannot be empty.")
    @Size(message = "Allows names between 2 and 30 characters long.", min=2, max=30)
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

    @Override
    public String toString() {
        return "Camper: \n" +
                ", user - '" + userName + '\'' + "\n" +
                ", place - '" + place.getPlaceName() + '\'' + "\n" +
                ", start - " + startDate + "\n" +
                ", end - " + endDate;
    }
}

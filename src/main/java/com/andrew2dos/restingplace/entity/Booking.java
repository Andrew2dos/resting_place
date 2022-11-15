package com.andrew2dos.restingplace.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bookings", schema = "public", catalog = "resting_place")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "start_date")
    private Date startDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Booking{ " +
//                "user=" + user.getUserName() +
                ", place=" + place.getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                 '}';
    }
}
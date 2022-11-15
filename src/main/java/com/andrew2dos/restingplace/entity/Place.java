package com.andrew2dos.restingplace.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "places", schema = "public", catalog = "resting_place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id's cannot be Null")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Place name's cannot be Null")
    @Column(name = "place_name")
    private String name;

}

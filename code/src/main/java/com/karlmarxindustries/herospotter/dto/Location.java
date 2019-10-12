package com.karlmarxindustries.herospotter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity(name="locations")
public class Location {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column(nullable = false)
    double latitude;
    @Column(nullable = false)
    double longitude;
    @Column
    String name;
    @Column
    String address;
}

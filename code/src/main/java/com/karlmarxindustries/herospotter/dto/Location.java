package com.karlmarxindustries.herospotter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="locations")
public class Location {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column
    double latitude;
    @Column
    double longitude;
    @Column
    String name;
    @Column
    String address;
    @Column(name="place_id")
    String placeId;

    public Location(String name, String address, String placeId) {
        this.name = name;
        this.address = address;
        this.placeId = placeId;
    }
}

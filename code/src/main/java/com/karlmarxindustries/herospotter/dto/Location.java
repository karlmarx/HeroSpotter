package com.karlmarxindustries.herospotter.dto;

import lombok.Data;

@Data
public class Location {
    int id;
    double latitude;
    double longitude;
    String name;
}

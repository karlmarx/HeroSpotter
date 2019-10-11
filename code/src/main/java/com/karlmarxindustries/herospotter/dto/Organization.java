package com.karlmarxindustries.herospotter.dto;

import lombok.Data;

@Data
public class Organization {
    int id;
    double latitude;
    double longitude;
    String name;
    String email;
    String url;
    String phoneNumber;
    String description;
}

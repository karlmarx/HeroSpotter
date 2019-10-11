package com.karlmarxindustries.herospotter.dto;

import lombok.Data;

@Data
public class Power {
    int id;
    String name;
    String description;
    boolean isUnique;
}

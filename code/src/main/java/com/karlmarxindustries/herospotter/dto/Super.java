package com.karlmarxindustries.herospotter.dto;

import lombok.Data;

import java.util.List;

@Data
public class Super  {
    int id;
    String name;
    String description;
    boolean isVillian;
    List<Power> powers;
    List<Organization> organizations;
}

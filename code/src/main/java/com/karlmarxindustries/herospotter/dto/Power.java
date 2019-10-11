package com.karlmarxindustries.herospotter.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="powers")
public class Power {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column(name="power_name", nullable = false)
    String name;
    @Column
    String description;
    @Column(name="is_unique")
    boolean isUnique;
}

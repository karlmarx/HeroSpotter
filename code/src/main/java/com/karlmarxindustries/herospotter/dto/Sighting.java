package com.karlmarxindustries.herospotter.dto;


import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;

@Data
@Entity(name="sightings")
public class Sighting {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column(nullable = false)
    LocalDate date;
    @ManyToOne
    @JoinColumn(name="super_id", nullable = false)
    Super super_;
    @ManyToOne
    @JoinColumn(name="location_id", nullable = false)
    Location location;
    @Column(name="image_file")
    Blob image;
    @Column(name="image_approved")
    boolean isApproved;
    @Column(name="reporter_name")
    String reporterName;
}

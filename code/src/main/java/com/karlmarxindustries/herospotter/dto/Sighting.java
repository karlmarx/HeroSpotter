package com.karlmarxindustries.herospotter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="sightings")
public class Sighting {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    @ManyToOne
    @JoinColumn(name="super_id", nullable = false)
    Super superPerson;
    @ManyToOne
    @JoinColumn(name="location_id", nullable = false)
    Location location;
    @Column(name="image_file")
    Blob image;
    @Column(name="image_approved")
    boolean isApproved;
    @Column(name="reporter_name")
    String reporterName;

    public Sighting(LocalDate date, Super superPerson, Location location, boolean isApproved, String reporterName) {
        this.date = date;
        this.superPerson = superPerson;
        this.location = location;

        this.isApproved = isApproved;
        this.reporterName = reporterName;
    }
}

package com.karlmarxindustries.herospotter.dto;


import lombok.Data;

import java.sql.Blob;
import java.time.LocalDate;

@Data
public class Sighting {
    LocalDate date;
    int superId;
    Super super_;
    int locationId;
    Location location;
    Blob image;
    boolean isApproved;
    String reporterName;
}

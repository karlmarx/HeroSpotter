package com.karlmarxindustries.herospotter;

import com.karlmarxindustries.herospotter.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LocationController {
    @Autowired
    SightingRepository sightings;
    @Autowired
    PowerRepository powers;
    @Autowired
    SuperRepository supers;
    @Autowired
    LocationRepository locations;
    @Autowired
    OrganizationRepository orgs;

}


package com.karlmarxindustries.herospotter.controller;

import com.karlmarxindustries.herospotter.dao.*;
import com.karlmarxindustries.herospotter.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MVCController {
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

    @GetMapping("/locations")
    public String displayLocations(Model model){
        List<Location> locationList = locations.findAll();
        model.addAttribute("locations",locationList);
        return "locations";
    }


}


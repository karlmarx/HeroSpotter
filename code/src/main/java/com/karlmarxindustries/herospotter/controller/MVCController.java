package com.karlmarxindustries.herospotter.controller;

import com.karlmarxindustries.herospotter.dao.*;
import com.karlmarxindustries.herospotter.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public String displayLocations(Model model) {
        List<Location> locationList = locations.findAll();
        model.addAttribute("locations", locationList);
        return "locations";
    }

    //    @PostMapping("/addLocation")
//public String addLocation (HttpServletRequest request) {
//    String name = request.getParameter("name");
//    String address = request.getParameter("address");
//    double latitude = Double.parseDouble(request.getParameter("latitude"));
//    double longitude = Double.parseDouble(request.getParameter("latitude"));
//    Location location = new Location();
//    location.setAddress(address);
//    location.setName(name);
//    location.setLatitude(latitude);
//    location.setLongitude(longitude);
//
//    }
    @PostMapping("/addLocation")
    public String addLocation(Location location) {
        locations.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizationList = orgs.findAll();
        model.addAttribute("organizations", organizationList);
        return "organizations";
    }

    @PostMapping("/addOrganization")
    public String addOrganization(Organization organization) {
        orgs.save(organization);
        return "redirect:/organizations";
    }
    @GetMapping("/powers")
    public String displayPowers(Model model) {
        List<Power> powerList = powers.findAll();
        model.addAttribute("powers", powerList);
        return "powers";
    }
    @GetMapping("/supers")
    public String displaySupers(Model model) {
        List<Super> superList = supers.findAll();
        model.addAttribute("supers", superList);
        List<Power> powerList = powers.findAll();
        model.addAttribute("powers", powerList);
        List<Organization> organizationList = orgs.findAll();
        model.addAttribute("organizations", organizationList);
        return "supers";
    }
    @GetMapping("/sightings")
    public String displaySightings(Model model) {
        List<Super> superList = supers.findAll();
        model.addAttribute("supers", superList);
        List<Location> locationList = locations.findAll();
        model.addAttribute("locations", locationList);
        List<Sighting> sightingList = sightings.findAll();
        model.addAttribute("sightings", sightingList);
        return "sightings";
    }
    @PostMapping("/addPowers")
    public String addPower(Power power, HttpServletRequest request) {
        if (request.getParameter("isUnique")!=null) {
            power.setUnique(true);
        } else {
            power.setUnique(false);
        }
        powers.save(power);
        return "redirect:/powers";
    }
    @PostMapping("/addSuper")
    public String addSuper(Super super_, HttpServletRequest request) {
        String[] powerIds = request.getParameterValues("powerId");
        String[] organizationIds = request.getParameterValues("organizationId");
        List<Organization> orgList = new ArrayList<>();
        for (String orgID : organizationIds) {
            orgList.add(orgs.findById(Integer.parseInt(orgID)).orElse(null));
        }
        List<Power> powerList = new ArrayList<>();
        for (String powerId : powerIds) {
            powerList.add(powers.findById(Integer.parseInt(powerId)).orElse(null));
        }
        super_.setOrganizations(orgList);
        super_.setPowers(powerList);
        if (request.getParameter("isVillain")!=null) {
            super_.setVillain(true);
        } else {
            super_.setVillain(false);
        }
        supers.save(super_);
        return "redirect:/supers";
    }
    @PostMapping("/addSighting")
    public String addSighting(Sighting sighting, HttpServletRequest request){
        String superId = request.getParameter("super");
        String locationId = request.getParameter("location");
        LocalDate localDate = LocalDate.parse(request.getParameter("date"));

        sighting.setDate(localDate);
        sighting.setSuperPerson(supers.findById(Integer.parseInt(superId)).orElse(null));
        sighting.setLocation(locations.findById(Integer.parseInt(locationId)).orElse(null));
        sighting.setApproved(false);
        sightings.save(sighting);
        return "redirect:/sightings";
    }
}


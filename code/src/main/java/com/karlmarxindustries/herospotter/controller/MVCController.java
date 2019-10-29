package com.karlmarxindustries.herospotter.controller;

import com.karlmarxindustries.herospotter.dao.*;
import com.karlmarxindustries.herospotter.dto.*;
import com.karlmarxindustries.herospotter.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    ServiceImpl service;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/")
    public String displayTop10(Model model) {
        List<Sighting> lastTenSightings = sightings.findFirst10ByOrderByIdDesc();
        model.addAttribute("sightings", lastTenSightings);
        return "index";
    }

    @GetMapping("/locations")
    public String displayLocations(Model model) {
        List<Location> locationList = locations.findAll();
        model.addAttribute("locations", locationList);
        return "locations";
    }

    @RequestMapping("/location/{id}")
    public String location(@PathVariable("id") String id, ModelMap model) {
        Location location = locations.findById(Integer.parseInt(id)).orElse(null);
        model.addAttribute("location", location);
        return "fragments/stylingFragment :: locationModalContents";
    }



    @PostMapping("/addLocation")
    public String addLocation(Location location, HttpServletRequest request) {
        location.setPlaceId(request.getParameter("placeID"));
        location.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        location.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        location = service.censorAndFillLocation(location);
        locations.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locations.findById(id).orElse(null);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("/editLocation")
    public String editLocationPartTwo(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locations.findById(id).orElse(null);
//        location.setLongitude(Double.parseDouble(request.getParameter("longitude")));
//        location.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        location.setName(request.getParameter("name"));
        location.setAddress(request.getParameter("address"));
        location.setPlaceId(request.getParameter("placeID"));
        Location filledInLocation = service.censorAndFillLocation(location);
        locations.save(filledInLocation);
        return "redirect:/locations";
    }

    @GetMapping("/deleteLocation")
    public String deleteLocation(Integer id) {
        List<Sighting> sightingsAtLocation = sightings.findByLocation_Id(id);
        sightings.deleteAll(sightingsAtLocation);
        locations.deleteById(id);
        return "redirect:/locations";
    }

    @RequestMapping("/organization/{id}")
    public String organization(@PathVariable("id") String id, ModelMap model) {
        Organization organization = orgs.findById(Integer.parseInt(id)).orElse(null);
        model.addAttribute("organization", organization);
        List<Super> superList = supers.findByOrganizations(organization);
        model.addAttribute("supers", superList);
        model.addAttribute("superNum", superList.size());
        return "fragments/stylingFragment :: organizationModalContents";
    }


    @GetMapping("/organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizationList = orgs.findAll();
        model.addAttribute("organizations", organizationList);
        return "organizations";
    }

    @PostMapping("/addOrganization")
    public String addOrganization(Organization organization, HttpServletRequest request) {
        organization.setPlaceId(request.getParameter("placeID"));
        Organization filledInOrg = service.censorAndFillOrg(organization);
        orgs.save(filledInOrg);
        return "redirect:/organizations";
    }

    @GetMapping("/editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = orgs.findById(id).orElse(null);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }

    @PostMapping("/editOrganization")
    public String editOrganizationPartTwo(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = orgs.findById(id).orElse(null);
        organization.setName(request.getParameter("name"));
        organization.setAddress(request.getParameter("address"));
        organization.setEmail(request.getParameter("email"));
        organization.setUrl(request.getParameter("url"));
        organization.setPhoneNumber(request.getParameter("phoneNumber"));
        organization.setPlaceId(request.getParameter("placeID"));
        organization.setDescription(request.getParameter("description"));
        Organization censoredOrg = service.censorAndFillOrg(organization);

//                make a generic method??
        orgs.save(censoredOrg);
        return "redirect:/organizations";
    }

    @GetMapping("/deleteOrganization")
    public String deleteOrganization(Integer id) {
        orgs.deleteById(id);
        return "redirect:/organizations";
    }

    @RequestMapping("/power/{id}")
    public String power(@PathVariable("id") String id, ModelMap model) {
        Power power = powers.findById(Integer.parseInt(id)).orElse(null);
        model.addAttribute("power", power);
        return "fragments/stylingFragment :: modalContents";
    }
    @GetMapping("/powers")
    public String displayPowers(Model model) {
        List<Power> powerList = powers.findAll();
        model.addAttribute("powers", powerList);
        return "powers";
    }

    @PostMapping("/addPowers")
    public String addPower(Power power, HttpServletRequest request) {
        if (request.getParameter("isUnique") != null) {
            power.setUnique(true);
        } else {
            power.setUnique(false);
        }
        power = service.censorAndFillPower(power);
        powers.save(power);
        return "redirect:/powers";
    }

    @GetMapping("/editPowers")
    public String editPowers(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = powers.findById(id).orElse(null);
        model.addAttribute("power", power);
        return "editPowers";
    }

    @PostMapping("/editPowers")
    public String editPowersPartTwo(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = powers.findById(id).orElse(null);
        power.setName(request.getParameter("name"));
        power.setDescription(request.getParameter("description"));
        if (request.getParameter("isUnique") != null) {
            power.setUnique(true);
        } else {
            power.setUnique(false);
        }
        power = service.censorAndFillPower(power);
        powers.save(power);
        return "redirect:/powers";
    }

    @GetMapping("/deletePower")
    public String deletePower(Integer id) {
        List<Super> supersWithPower = supers.findByPowersContains(powers.findById(id).orElse(null));
        for (Super each : supersWithPower) {
           each.getPowers().remove(powers.findById(id).orElse(null));
        }
        supers.saveAll(supersWithPower);
            powers.deleteById(id);
        return "redirect:/powers";
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
    @RequestMapping("/super/{id}")
    public String superDisplay(@PathVariable("id") String id, ModelMap model) {
        Super super_ = supers.findById(Integer.parseInt(id)).orElse(null);
        model.addAttribute("super", super_);
        List<Power> powerList = powers.findBySuperMembers(super_);
        List<Organization> orgsList = orgs.findBySuperMembers(super_);
        model.addAttribute("powers", powerList);
        model.addAttribute("orgs", orgsList);
        model.addAttribute("powerNum", powerList.size());
        model.addAttribute("orgNum", orgsList.size());
        return "fragments/stylingFragment :: superModalContents";
//        Power power = powers.findById(Integer.parseInt(id)).orElse(null);
//        model.addAttribute("power", power);
//        return "fragments/stylingFragment :: modalContents";
    }
    @PostMapping("/addSuper")
    public String addSuper(Super super_, HttpServletRequest request, RedirectAttributes redirAttrs) {
        String[] powerIds = request.getParameterValues("addPowerId");
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
        if (request.getParameter("isVillain") != null) {
            super_.setVillain(true);
        } else {
            super_.setVillain(false);
        }
        super_.setName(request.getParameter("super-name"));
        super_ = service.censorAndFillSuper(super_);
        supers.save(super_);
        redirAttrs.addFlashAttribute("message", "Superhuman has been successfully added.");

        return "redirect:/supers";
    }

    @GetMapping("/editSuper")
    public String editSuper(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super super_ = supers.findById(id).orElse(null);
        model.addAttribute("super", super_);
        model.addAttribute("villain", super_.isVillain());
        List<Power> powerList = powers.findAll();
        List<Power> powersCurrent = powers.findBySuperMembers(super_);
        List<Power> powersNotHad = new ArrayList<>();
        for (Power pow: powerList) {
            if (!powersCurrent.contains(pow)) {
                powersNotHad.add(pow);
            }
        }
        model.addAttribute("powerscurrent", powersCurrent);
        model.addAttribute("powersmissing", powersNotHad);
//        model.addAttribute("powers", powerList);
        List<Organization> organizationList = orgs.findAll();

//        model.addAttribute("organizations", organizationList);
        List<Organization> orgsCurrent = orgs.findBySuperMembers(super_);
        List<Organization> orgsNotIn = new ArrayList<>();
        for (Organization org : organizationList) {
            if (!orgsCurrent.contains(org)) {
                orgsNotIn.add(org);
            }
        }
        model.addAttribute("orgscurrent", orgsCurrent);
        model.addAttribute("orgsnotin", orgsNotIn);
        return "editSuper";
    }

    @PostMapping("/editSuper")
    public String editSuperPartTwo(HttpServletRequest request, RedirectAttributes redirAttrs) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super super_ = supers.findById(id).orElse(null);
        super_.setName(request.getParameter("name"));
        super_.setDescription(request.getParameter("description"));
        if (request.getParameter("isVillain") != null) {
            super_.setVillain(true);
        } else {
            super_.setVillain(false);
        }
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
        super_ = service.censorAndFillSuper(super_);
        supers.save(super_);
        redirAttrs.addFlashAttribute("message", "Superhuman has been successfully edited.");

        return "redirect:/supers";
    }

    @GetMapping("/deleteSuper")
    public String deleteSuper(Integer id, RedirectAttributes redirAttrs) {
        List<Sighting> sightingsBySuper = sightings.findBySuperPerson(supers.getOne(id));
        sightings.deleteAll(sightingsBySuper);
        supers.deleteById(id);
        redirAttrs.addFlashAttribute("message", "Superhuman has been successfully deleted.");

        return "redirect:/supers";
    }

    @GetMapping("/sightings")
    public String displaySightings(Model model) {
        List<Super> superList = supers.findAll();
        model.addAttribute("supers", superList);
        List<Location> locationList = locations.findAll();
        model.addAttribute("locations", locationList);
        List<Sighting> sightingList = sightings.findAll();
        model.addAttribute("sightings", sightingList);

        List<Power> powerList = powers.findAll();
        model.addAttribute("powers", powerList);
        return "sightings";
    }
    @RequestMapping("/sighting/{id}")
    public String sighting(@PathVariable("id") String id, ModelMap model) {
        Sighting sighting = sightings.findById(Integer.parseInt(id)).orElse(null);
        model.addAttribute("sighting", sighting);
        Super sightingSuper = sighting.getSuperPerson();
        Location sightingLocation = sighting.getLocation();
        model.addAttribute("super", sightingSuper);
        model.addAttribute("location", sightingLocation);
        return "fragments/stylingFragment :: sightingModalContents";

    }

    @PostMapping("/addSighting")
    public String addSighting(Sighting sighting, HttpServletRequest request, RedirectAttributes redirAttrs) {
        String superId = request.getParameter("super");
        String locationId = request.getParameter("location");
        LocalDate localDate = LocalDate.parse(request.getParameter("date"));
        sighting.setDate(localDate);
        sighting.setSuperPerson(supers.findById(Integer.parseInt(superId)).orElse(null)); //is this right way to use or else?
        sighting.setLocation(locations.findById(Integer.parseInt(locationId)).orElse(null));
        sighting.setApproved(false);
        sighting.setReporterName(request.getParameter("reporterName"));
        sighting = service.censorAndFillSighting(sighting);
        sightings.save(sighting);
        redirAttrs.addFlashAttribute("message", "Sighting has been successfully added.");

        return "redirect:/sightings";
    }
    @GetMapping("/editSighting")
    public String editSighting(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightings.findById(id).orElse(null);
        model.addAttribute("sighting", sighting);
        List<Super> superList = supers.findAll();
        model.addAttribute("supers", superList);
        List<Location> locationList = locations.findAll();
        model.addAttribute("locations", locationList);
        return "editSighting";
    }


    @PostMapping("/editSighting")
    public String editSightingPartTwo(HttpServletRequest request, RedirectAttributes redirAttrs) {
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = sightings.findById(id).orElse(null);
        sighting.setDate(LocalDate.parse(request.getParameter("date")));
        String superId = request.getParameter("super");
        String locationId = request.getParameter("location");
        sighting.setSuperPerson(supers.findById(Integer.parseInt(superId)).orElse(null)); //is this right way to use or else?
        sighting.setLocation(locations.findById(Integer.parseInt(locationId)).orElse(null));
        sighting.setApproved(Boolean.parseBoolean(request.getParameter("approved")));
        sighting = service.censorAndFillSighting(sighting);
        sightings.save(sighting);
        redirAttrs.addFlashAttribute("message", "Sighting has been successfully edited.");

        return "redirect:/sightings";
    }

    @GetMapping("/deleteSighting")
    public String deleteSighting(Integer id, RedirectAttributes redirAttrs) {
        sightings.deleteById(id);
        redirAttrs.addFlashAttribute("message", "Sighting has been successfully deleted.");

        return "redirect:/sightings";
    }


}


package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Location;

import java.util.List;

public interface LocationDao {
    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(Location location);
}

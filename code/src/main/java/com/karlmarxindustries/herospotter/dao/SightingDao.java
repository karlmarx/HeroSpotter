package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Sighting;
import com.karlmarxindustries.herospotter.dto.Super;

import java.time.LocalDate;
import java.util.List;

public interface SightingDao {
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting course);
    void updateSighting(Sighting course);
    void deleteSightingById(int id);

    List<Sighting> findBySuper(Super super);
    List<Sighting> findByDate(LocalDate date);
}

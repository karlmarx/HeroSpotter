package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Sighting;
import com.karlmarxindustries.herospotter.dto.Super;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    List<Sighting> findBySuper(Super super_);
    List<Sighting> findByDate(LocalDate date);
}

package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Sighting;
import com.karlmarxindustries.herospotter.dto.Super;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    List<Sighting> findBySuperPerson(Super superPerson);
    List<Sighting> findByDate(LocalDate date);
//    Page<Sighting> findByDate(LocalDate date, Pageable pageable);
    List<Sighting> findFirst10ByOrderByIdDesc ();
    List<Sighting> findByLocation_Id(int id);
}

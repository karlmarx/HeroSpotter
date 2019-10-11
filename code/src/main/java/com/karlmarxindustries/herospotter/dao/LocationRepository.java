package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}

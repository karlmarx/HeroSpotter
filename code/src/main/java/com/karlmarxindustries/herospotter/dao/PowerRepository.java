package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Power;
import com.karlmarxindustries.herospotter.dto.Super;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PowerRepository extends JpaRepository<Power, Integer> {
    public List<Power> findBySuperMembers(Super superperson);
}

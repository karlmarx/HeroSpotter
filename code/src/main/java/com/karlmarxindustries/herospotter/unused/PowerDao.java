package com.karlmarxindustries.herospotter.unused;

import com.karlmarxindustries.herospotter.dto.Power;
import com.karlmarxindustries.herospotter.dto.Super;

import java.util.List;

public interface PowerDao {
    Power getPowerById(int id);
    List<Power> getAllPowers();
    Power addPower(Power course);
    void updatePower(Power course);
    void deletePowerById(int id);

    List<Power> getPowersForSuper(Super super_);

}

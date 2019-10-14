package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepo;



    @BeforeEach
    public  void setUp() {
        locationRepo.deleteAll();
        Location location = new Location(1,"The Software Guild", "address", "");
        locationRepo.save(location);
    }
    @Test
    public void testGetOne() {
        List<Location> shouldHaveOne =  locationRepo.findAll();
        Assertions.assertEquals(1, shouldHaveOne.size());
    }
}

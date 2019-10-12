package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LocationRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private LocationRepository locationRepo;



    @BeforeAll
    public void setUp() {
        Location location = new Location(1,38.2550387d,-85.7603587d,"The Software Guild");
        testEntityManager.persist(location);
    }
    @Test
    public void testGetOne() {
        List<Location> shouldHaveOne =  locationRepo.findAll();
        Assertions.assertEquals(1, shouldHaveOne.size());
    }
}

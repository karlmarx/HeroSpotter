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
    }
    @Test
    public void testSaveOneGetAll() {
        Location location = new Location(1, "The Software Guild", "address", "");
        locationRepo.save(location);
        List<Location> shouldHaveOne = locationRepo.findAll();
        Assertions.assertEquals(1, shouldHaveOne.size());
    }
    @Test
    public void testSaveManyGetAll() {
        Location location = new Location(1, "The Software Guild", "address", "asdf");
        Location location2 = new Location(2, "Karl MArx, Industries", "123 e main st ", "asdf");
        Location location3 = new Location(3, "The Software Guild", "address", "asdfasdfadfadsfasdfdsfaf");
        locationRepo.save(location);
        locationRepo.save(location2);
        locationRepo.save(location3);
        List<Location> shouldHaveThree = locationRepo.findAll();
        Assertions.assertEquals(3, shouldHaveThree.size());
    }
    @Test
    public void testFindByID() {
        Location location = new Location(1, "The Software Guild", "address", "asdf");
        Location location2 = new Location(2, "Karl MArx, Industries", "123 e main st ", "asdf");
        Location location3 = new Location(3, "The Software Guild", "address", "asdfasdfadfadsfasdfdsfaf");
        location = locationRepo.save(location);
        location2 = locationRepo.save(location2);
        location3 = locationRepo.save(location3);
        Location shouldBeLocation = locationRepo.findById(location.getId()).orElse(null);
        Location shouldBeLocation2 = locationRepo.findById(location2.getId()).orElse(null);
        Location shouldBeLocation3 = locationRepo.findById(location3.getId()).orElse(null);
        Assertions.assertEquals(location, shouldBeLocation);
        Assertions.assertEquals(location2, shouldBeLocation2);
        Assertions.assertEquals(location3, shouldBeLocation3);
    }
    @Test
    public void testSavedUpdatesObject() {
        Location location = new Location(1, "The Software Guild", "address", "asdf");
        location = locationRepo.save(location);
        Location shouldBe1 = locationRepo.findById(location.getId()).orElse(null);
        Assertions.assertEquals(location, shouldBe1);
        shouldBe1.setName("blech");
        locationRepo.save(shouldBe1);
        Location shouldBe1Modified = locationRepo.findById(location.getId()).orElse(null);
        long objectCount = locationRepo.count();
        Assertions.assertEquals(shouldBe1Modified, shouldBe1);
        Assertions.assertEquals(1, objectCount);
    }
    @Test
    public void testDelete() {
        Location location = new Location(1, "The Software Guild", "address", "asdf");
        Location location2 = new Location(2, "Karl MArx, Industries", "123 e main st ", "asdf");
        Location location3 = new Location(3, "The Software Guild", "address", "asdfasdfadfadsfasdfdsfaf");
        location = locationRepo.save(location);
        location2 = locationRepo.save(location2);
        location3 = locationRepo.save(location3);
        long shouldBe3 = locationRepo.count();
        locationRepo.delete(location);
        locationRepo.delete(location2);
        locationRepo.delete(location3);
        long shouldBe0 = locationRepo.count();
        Assertions.assertEquals(0, shouldBe0);
        Assertions.assertEquals(3, shouldBe3);
        Location locationNew = new Location(7, "The West Software Guild", "address", "asdf");
        locationNew = locationRepo.save(locationNew);
        locationRepo.deleteById(locationNew.getId());
        long shouldBe0Again = locationRepo.count();
        Assertions.assertEquals(0, shouldBe0Again);
    }
}

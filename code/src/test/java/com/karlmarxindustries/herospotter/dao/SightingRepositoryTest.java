package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Location;
import com.karlmarxindustries.herospotter.dto.Sighting;
import com.karlmarxindustries.herospotter.dto.Super;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SightingRepositoryTest {

    @Autowired
    SightingRepository sightings;
    @Autowired
    LocationRepository locations;
    @Autowired
    SuperRepository supers;


    @BeforeEach
    public  void setUp() {
        sightings.deleteAll();
        supers.deleteAll();

        Super super_ = new Super("jon", "jerkface", false);
        Super super_2 = new Super("ronald reagan", "jerk", true);
        Super super_3 = new Super("trotsky", "good guy", false);
        supers.save(super_);
        supers.save(super_2);
        supers.save(super_3);
        locations.deleteAll();

        Location location = new Location(1, "The Software Guild", "address", "asdf");
        Location location2 = new Location(2, "Karl MArx, Industries", "123 e main st ", "asdf");
        Location location3 = new Location(3, "The Software Guild", "address", "asdfasdfadfadsfasdfdsfaf");
        locations.save(location);
        locations.save(location2);
        locations.save(location3);


    }
    @Test
    void testFindBySuperPerson() {
        List<Super> superList = supers.findAll();
        List<Location> locationList = locations.findAll();
        Sighting sighting0 = new Sighting(LocalDate.now(), superList.get(0), locationList.get(0), false, "jon doe");
        Sighting sighting1 = new Sighting(LocalDate.of(2019,10,26), superList.get(0), locationList.get(1), true, "friedrich engels");
        Sighting sighting2 = new Sighting(LocalDate.of(2018,10,26), superList.get(2), locationList.get(2), true, "MIKE!");
        sightings.save(sighting0);
        sightings.save(sighting1);
        sightings.save(sighting2);
        List<Sighting> shouldBe2Sightings = sightings.findBySuperPerson(superList.get(0));
        Assertions.assertEquals(2, shouldBe2Sightings.size());
        Assertions.assertTrue(shouldBe2Sightings.contains(sighting0));
        Assertions.assertTrue(shouldBe2Sightings.contains(sighting1));
        Assertions.assertFalse(shouldBe2Sightings.contains(sighting2));
    }

    @Test
    void findByDate() {
        List<Super> superList = supers.findAll();
        List<Location> locationList = locations.findAll();
        Sighting sighting0 = new Sighting(LocalDate.now(), superList.get(0), locationList.get(0), false, "jon doe");
        Sighting sighting1 = new Sighting(LocalDate.of(2019,10,26), superList.get(0), locationList.get(1), true, "friedrich engels");
        Sighting sighting2 = new Sighting(LocalDate.of(2019,10,26), superList.get(2), locationList.get(2), true, "MIKE!");
        sightings.save(sighting0);
        sightings.save(sighting1);
        sightings.save(sighting2);
        List<Sighting> shouldBe2Sightings = sightings.findByDate(LocalDate.of(2019,10,26));
        Assertions.assertEquals(2, shouldBe2Sightings.size());
        Assertions.assertTrue(shouldBe2Sightings.contains(sighting1));
        Assertions.assertTrue(shouldBe2Sightings.contains(sighting2));
        Assertions.assertFalse(shouldBe2Sightings.contains(sighting0));
    }
    @Test
    public void testSaveOneGetAll() {
        //Sighting(LocalDate date, Super superPerson, Location location, Blob image, boolean isApproved, String reporterName)
        List<Super> superList = supers.findAll();
        List<Location> locationList = locations.findAll();
        Sighting sighting0 = new Sighting(LocalDate.now(), superList.get(0), locationList.get(0), false, "jon doe");
        sightings.save(sighting0);
        List<Sighting> shouldHaveOne = sightings.findAll();
        Assertions.assertEquals(1, shouldHaveOne.size());
    }
    @Test
    public void testSaveManyGetAll() {
        List<Super> superList = supers.findAll();
        List<Location> locationList = locations.findAll();
        Sighting sighting0 = new Sighting(LocalDate.now(), superList.get(0), locationList.get(0), false, "jon doe");
        Sighting sighting1 = new Sighting(LocalDate.of(2019,10,26), superList.get(1), locationList.get(1), true, "friedrich engels");
        Sighting sighting2 = new Sighting(LocalDate.of(2018,10,26), superList.get(2), locationList.get(2), true, "MIKE!");
        sightings.save(sighting0);
        sightings.save(sighting1);
        sightings.save(sighting2);
        List<Sighting> shouldHaveThree = sightings.findAll();
        Assertions.assertEquals(3, shouldHaveThree.size());
    }
    @Test
    public void testFindByID() {
        List<Super> superList = supers.findAll();
        List<Location> locationList = locations.findAll();
        Sighting sighting = new Sighting(LocalDate.now(), superList.get(0), locationList.get(0), false, "jon doe");
        Sighting sighting1 = new Sighting(LocalDate.of(2019,10,26), superList.get(1), locationList.get(1), true, "friedrich engels");
        Sighting sighting2 = new Sighting(LocalDate.of(2018,10,26), superList.get(2), locationList.get(2), true, "MIKE!");
        sightings.save(sighting);
        sightings.save(sighting1);
        sightings.save(sighting2);
        Sighting shouldBeSighting = sightings.findById(sighting.getId()).orElse(null);
        Sighting shouldBeSighting1 = sightings.findById(sighting1.getId()).orElse(null);
        Sighting shouldBeSighting2 = sightings.findById(sighting2.getId()).orElse(null);
        Assertions.assertEquals(sighting, shouldBeSighting);
        Assertions.assertEquals(sighting1, shouldBeSighting1);
        Assertions.assertEquals(sighting2, shouldBeSighting2);
    }
    @Test
    public void testSavedUpdatesObject() {
        List<Super> superList = supers.findAll();
        List<Location> locationList = locations.findAll();
        Sighting sighting = new Sighting(LocalDate.now(), superList.get(0), locationList.get(0), false, "jon doe");
        sighting = sightings.save(sighting);
        Sighting shouldBe1 = sightings.findById(sighting.getId()).orElse(null);
        Assertions.assertEquals(sighting, shouldBe1);
        shouldBe1.setReporterName("blech");
        sightings.save(shouldBe1);
        Sighting shouldBe1Modified = sightings.findById(sighting.getId()).orElse(null);
        long objectCount = sightings.count();
        Assertions.assertEquals(shouldBe1Modified, shouldBe1);
        Assertions.assertEquals(1, objectCount);
    }
    @Test
    public void testDelete() {
        List<Super> superList = supers.findAll();
        List<Location> locationList = locations.findAll();
        Sighting sighting = new Sighting(LocalDate.now(), superList.get(0), locationList.get(0), false, "jon doe");
        Sighting sighting1 = new Sighting(LocalDate.of(2019,10,26), superList.get(1), locationList.get(1), true, "friedrich engels");
        Sighting sighting2 = new Sighting(LocalDate.of(2018,10,26), superList.get(2), locationList.get(2), true, "MIKE!");
        sighting = sightings.save(sighting);
        sighting2 = sightings.save(sighting2);
        sighting1 = sightings.save(sighting1);
        long shouldBe3 = sightings.count();
        sightings.delete(sighting);
        sightings.delete(sighting2);
        sightings.delete(sighting1);
        long shouldBe0 = sightings.count();
        Assertions.assertEquals(0, shouldBe0);
        Assertions.assertEquals(3, shouldBe3);
        Sighting sightingNew = new Sighting(LocalDate.of(2018,10,15), superList.get(0), locationList.get(1), true, "basjdflaksdjf!");
        sightingNew = sightings.save(sightingNew);
        sightings.deleteById(sightingNew.getId());
        long shouldBe0Again = sightings.count();
        Assertions.assertEquals(0, shouldBe0Again);
    }
}
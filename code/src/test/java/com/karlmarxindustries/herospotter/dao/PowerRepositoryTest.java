//package com.karlmarxindustries.herospotter.dao;
//
//import com.karlmarxindustries.herospotter.dto.Power;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class PowerRepositoryTest {
//
//    @Autowired
//    private PowerRepository powers;
//
//    @Test
//    void findBySuperMembers() {
//    }
//
//    @BeforeEach
//    public  void setUp() {
//        powers.deleteAll();
//    }
//    @Test
//    public void testSaveOneGetAll() {
//        Power power = new Power();
//        powers.save(power);
//        List<Power> shouldHaveOne = powers.findAll();
//        Assertions.assertEquals(1, shouldHaveOne.size());
//    }
//    @Test
//    public void testSaveManyGetAll() {
//        Power power = new Power(1, "The Software Guild", "address", "asdf");
//        Power power2 = new Power(2, "Karl MArx, Industries", "123 e main st ", "asdf");
//        Power power3 = new Power(3, "The Software Guild", "address", "asdfasdfadfadsfasdfdsfaf");
//        powers.save(power);
//        powers.save(power2);
//        powers.save(power3);
//        List<Power> shouldHaveThree = powers.findAll();
//        Assertions.assertEquals(3, shouldHaveThree.size());
//    }
//    @Test
//    public void testFindByID() {
//        Power power = new Power(1, "The Software Guild", "address", "asdf");
//        Power power2 = new Power(2, "Karl MArx, Industries", "123 e main st ", "asdf");
//        Power power3 = new Power(3, "The Software Guild", "address", "asdfasdfadfadsfasdfdsfaf");
//        power = powers.save(power);
//        power2 = powers.save(power2);
//        power3 = powers.save(power3);
//        Power shouldBePower = powers.findById(power.getId()).orElse(null);
//        Power shouldBePower2 = powers.findById(power2.getId()).orElse(null);
//        Power shouldBePower3 = powers.findById(power3.getId()).orElse(null);
//        Assertions.assertEquals(power, shouldBePower);
//        Assertions.assertEquals(power2, shouldBePower2);
//        Assertions.assertEquals(power3, shouldBePower3);
//    }
//    @Test
//    public void testSavedUpdatesObject() {
//        Power power = new Power(1, "The Software Guild", "address", "asdf");
//        power = powers.save(power);
//        Power shouldBe1 = powers.findById(power.getId()).orElse(null);
//        Assertions.assertEquals(power, shouldBe1);
//        shouldBe1.setName("blech");
//        powers.save(shouldBe1);
//        Power shouldBe1Modified = powers.findById(power.getId()).orElse(null);
//        long objectCount = powers.count();
//        Assertions.assertEquals(shouldBe1Modified, shouldBe1);
//        Assertions.assertEquals(1, objectCount);
//    }
//    @Test
//    public void testDelete() {
//        Power power = new Power(1, "The Software Guild", "address", "asdf");
//        Power power2 = new Power(2, "Karl MArx, Industries", "123 e main st ", "asdf");
//        Power power3 = new Power(3, "The Software Guild", "address", "asdfasdfadfadsfasdfdsfaf");
//        power = powers.save(power);
//        power2 = powers.save(power2);
//        power3 = powers.save(power3);
//        long shouldBe3 = powers.count();
//        powers.delete(power);
//        powers.delete(power2);
//        powers.delete(power3);
//        long shouldBe0 = powers.count();
//        Assertions.assertEquals(0, shouldBe0);
//        Assertions.assertEquals(3, shouldBe3);
//        Power powerNew = new Power(7, "The West Software Guild", "address", "asdf");
//        powerNew = powers.save(powerNew);
//        powers.deleteById(powerNew.getId());
//        long shouldBe0Again = powers.count();
//        Assertions.assertEquals(0, shouldBe0Again);
//    }
//}
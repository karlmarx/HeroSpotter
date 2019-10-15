package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Power;
import com.karlmarxindustries.herospotter.dto.Super;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PowerRepositoryTest {

    @Autowired
    private PowerRepository powers;
    @Autowired
    private SuperRepository supers;

    @Test
    void testFindBySuperMembers() {
        Power power = new Power("Cultural Revolution", "Lorem ipsum dolor amet lumbersexual thundercats mlkshk umami cray fashion axe asymmetrical", true);
        Power power2 = new Power("Proletarian Internationalism", "Offal selvage messenger bag", false);
        Power power3 = new Power("underfunding AIDS", "lexitarian readymade DIY tofu tattooed locavore forage. Polaroid meditation kogi umami trust fun", false);
        powers.save(power);
        powers.save(power2);
        powers.save(power3);
        Super super_ = new Super("jon", "jerkface", false);
        Super super_2 = new Super("ronald reagan", "jerk", true);
        super_2.setPowers(new ArrayList<Power>(Arrays.asList(power,power3)));
        super_.setPowers(new ArrayList<Power>(Arrays.asList(power2)));
        Super super_3 = new Super("trotsky", "good guy", false);
        supers.save(super_);
        supers.save(super_2);
        supers.save(super_3);
        List<Power> shouldBePowerAndPower3 = powers.findBySuperMembers(super_2);
        List<Power> shouldBePower2 = powers.findBySuperMembers(super_);
        List<Power> shouldBeEmpty = powers.findBySuperMembers(super_3);
        Assertions.assertEquals(2, shouldBePowerAndPower3.size());
        Assertions.assertEquals(1, shouldBePower2.size());
        Assertions.assertEquals(0, shouldBeEmpty.size());
        Assertions.assertTrue(shouldBePowerAndPower3.contains(power) && shouldBePowerAndPower3.contains(power3));
        Assertions.assertTrue(shouldBePower2.contains(power2));
    }

    @BeforeEach
    public  void setUp() {
        powers.deleteAll();
    }
    @Test
    public void testSaveOneGetAll() {
        Power power = new Power("Cultural Revolution", "Lorem ipsum dolor amet lumbersexual thundercats mlkshk umami cray fashion axe asymmetrical", true);
        powers.save(power);
        List<Power> shouldHaveOne = powers.findAll();
        Assertions.assertEquals(1, shouldHaveOne.size());
    }
    @Test
    public void testSaveManyGetAll() {
        Power power = new Power("Cultural Revolution", "Lorem ipsum dolor amet lumbersexual thundercats mlkshk umami cray fashion axe asymmetrical", true);
        Power power2 = new Power("Proletarian Internationalism", "Offal selvage messenger bag", false);
        Power power3 = new Power("underfunding AIDS", "lexitarian readymade DIY tofu tattooed locavore forage. Polaroid meditation kogi umami trust fun", false);
        powers.save(power);
        powers.save(power2);
        powers.save(power3);
        List<Power> shouldHaveThree = powers.findAll();
        Assertions.assertEquals(3, shouldHaveThree.size());
    }
    @Test
    public void testFindByID() {
        Power power = new Power("Cultural Revolution", "Lorem ipsum dolor amet lumbersexual thundercats mlkshk umami cray fashion axe asymmetrical", false);
        Power power2 = new Power("Proletarian Internationalism", "Offal selvage messenger bag, activated charcoal PBR&B tattooed freegan hoodie mixtape seitanr prism pitchfork four dollar toast church-key tumeri", false);
        Power power3 = new Power("underfunding AIDS", "lexitarian readymade DIY tofu tattooed locavore forage. Polaroid meditation kogi umami trust fun", false);
        power = powers.save(power);
        power2 = powers.save(power2);
        power3 = powers.save(power3);
        Power shouldBePower = powers.findById(power.getId()).orElse(null);
        Power shouldBePower2 = powers.findById(power2.getId()).orElse(null);
        Power shouldBePower3 = powers.findById(power3.getId()).orElse(null);
        Assertions.assertEquals(power, shouldBePower);
        Assertions.assertEquals(power2, shouldBePower2);
        Assertions.assertEquals(power3, shouldBePower3);
    }
    @Test
    public void testSavedUpdatesObject() {
        Power power = new Power("Cultural Revolution", "Lorem ipsum dolor amet lumbersexual thundercats mlkshk umami cray fashion axe asymmetrical", false);
        power = powers.save(power);
        Power shouldBe1 = powers.findById(power.getId()).orElse(null);
        Assertions.assertEquals(power, shouldBe1);
        shouldBe1.setName("blech");
        powers.save(shouldBe1);
        Power shouldBe1Modified = powers.findById(power.getId()).orElse(null);
        long objectCount = powers.count();
        Assertions.assertEquals(shouldBe1Modified, shouldBe1);
        Assertions.assertEquals(1, objectCount);
    }
    @Test
    public void testDelete() {
        Power power = new Power("Cultural Revolution", "Lorem ipsum dolor amet lumbersexual thundercats mlkshk umami cray fashion axe asymmetrical", false);
        Power power2 = new Power("Proletarian Internationalism", "Offal selvage messenger bag, activated charcoal PBR&B tattooed freegan hoodie mixtape seitanr prism pitchfork four dollar toast church-key tumeri", false);
        Power power3 = new Power("underfunding AIDS", "lexitarian readymade DIY tofu tattooed locavore forage. Polaroid meditation kogi umami trust fun", false);
        power = powers.save(power);
        power2 = powers.save(power2);
        power3 = powers.save(power3);
        long shouldBe3 = powers.count();
        powers.delete(power);
        powers.delete(power2);
        powers.delete(power3);
        long shouldBe0 = powers.count();
        Assertions.assertEquals(0, shouldBe0);
        Assertions.assertEquals(3, shouldBe3);
        Power powerNew = new Power("Monkey Handling", "lkshk quinoa raw denim. Fingerstache gastropub fanny pack, chia trust fund wolf 8-bit ethical keffiyeh dreamcatcher hella helvetica vinyl enamel pin quinoa. Etsy vape mess", true);
        powerNew = powers.save(powerNew);
        powers.deleteById(powerNew.getId());
        long shouldBe0Again = powers.count();
        Assertions.assertEquals(0, shouldBe0Again);
    }
}
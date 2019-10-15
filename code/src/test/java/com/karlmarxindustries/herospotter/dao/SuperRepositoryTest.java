package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Organization;
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
class SuperRepositoryTest {

  

    @Autowired
    private SuperRepository supers;
    @Autowired
    private OrganizationRepository orgs;

    @Test
    public void testFindByOrganizations(){
        Organization organization = new Organization( "The Communist Party", "commies@workers.org", "http://www.marxists.org", "+12125551212", "the best", "1 police plaza, new york, ny, 10011", "longcodeaksjdflkajshdflkahsd");
        Organization organization2 = new Organization( "The Communist Party of China", "commies@workers.cn", "http://www.marxists.cn", "+86-2125551212", "the worst", "1 police plaza, beijing", "longcodeaksjdflkajshdflkahsd");
        Organization organization3 = new Organization( "The Communist Party ofCanada", "commies@workers.ca", "http://www.marxists.ca", "+15145551212", "the best, eh", "1 police plaza, ottawa, on, canada", "sdfasdfasdfasfdasdffasdf");
        orgs.save(organization);
        orgs.save(organization2);
        orgs.save(organization3);
        Super super_ = new Super("jon", "jerkface", false);
        Super super_2 = new Super("ronald reagan", "jerk", true);
        Super super_3 = new Super("trotsky", "good guy", false);
        super_3.setOrganizations(new ArrayList<>(Arrays.asList(organization3)));
        super_.setOrganizations(new ArrayList<>(Arrays.asList(organization3, organization)));
        supers.save(super_);
        supers.save(super_2);
        supers.save(super_3);
        List<Organization> shouldBeOrgAndOrg3 = orgs.findBySuperMembers(super_);
        List<Organization> shouldBeOrg = orgs.findBySuperMembers(super_3);
        List<Organization> shouldBeEmpty = orgs.findBySuperMembers(super_2);
        Assertions.assertEquals(2, shouldBeOrgAndOrg3.size());
        Assertions.assertEquals(1, shouldBeOrg.size());
        Assertions.assertEquals(0, shouldBeEmpty.size());
        Assertions.assertTrue(shouldBeOrgAndOrg3.contains(organization) && shouldBeOrgAndOrg3.contains(organization3));
        Assertions.assertTrue(shouldBeOrg.contains(organization));

    }

    @BeforeEach
    public  void setUp() {
        supers.deleteAll();
    }
    @Test
    public void testSaveOneGetAll() {
        Super super_ = new Super("jon", "jerkface", false);
        supers.save(super_);
        List<Super> shouldHaveOne = supers.findAll();
        Assertions.assertEquals(1, shouldHaveOne.size());
    }
    @Test
    public void testSaveManyGetAll() {
        Super super_ = new Super("jon", "jerkface", false);
        Super super_2 = new Super("ronald reagan", "jerk", true);
        Super super_3 = new Super("trotsky", "good guy", false);
        supers.save(super_);
        supers.save(super_2);
        supers.save(super_3);
        List<Super> shouldHaveThree = supers.findAll();
        Assertions.assertEquals(3, shouldHaveThree.size());
    }
    @Test
    public void testFindByID() {
        Super super_ = new Super("jon", "jerkface", false);
        Super super_2 = new Super("ronald reagan", "jerk", true);
        Super super_3 = new Super("trotsky", "good guy", false);
        super_ = supers.save(super_);
        super_2 = supers.save(super_2);
        super_3 = supers.save(super_3);
        Super shouldBeSuper = supers.findById(super_.getId()).orElse(null);
        Super shouldBeSuper2 = supers.findById(super_2.getId()).orElse(null);
        Super shouldBeSuper3 = supers.findById(super_3.getId()).orElse(null);
        Assertions.assertEquals(super_, shouldBeSuper);
        Assertions.assertEquals(super_2, shouldBeSuper2);
        Assertions.assertEquals(super_3, shouldBeSuper3);
    }
    @Test
    public void testSavedUpdatesObject() {
        Super super_ = new Super("jon", "jerkface", false);
        super_ = supers.save(super_);
        Super shouldBe1 = supers.findById(super_.getId()).orElse(null);
        Assertions.assertEquals(super_, shouldBe1);
        shouldBe1.setName("blech");
        supers.save(shouldBe1);
        Super shouldBe1Modified = supers.findById(super_.getId()).orElse(null);
        long objectCount = supers.count();
        Assertions.assertEquals(shouldBe1Modified, shouldBe1);
        Assertions.assertEquals(1, objectCount);
    }
    @Test
    public void testDelete() {
        Super super_ = new Super("jon", "jerkface", false);
        Super super_2 = new Super("ronald reagan", "jerk", true);
        Super super_3 = new Super("trotsky", "good guy", false);
        super_ = supers.save(super_);
        super_2 = supers.save(super_2);
        super_3 = supers.save(super_3);
        long shouldBe3 = supers.count();
        supers.delete(super_);
        supers.delete(super_2);
        supers.delete(super_3);
        long shouldBe0 = supers.count();
        Assertions.assertEquals(0, shouldBe0);
        Assertions.assertEquals(3, shouldBe3);
        Super super_New = new Super("karl", "asdfasdfasdfasdf", false);
        super_New = supers.save(super_New);
        supers.deleteById(super_New.getId());
        long shouldBe0Again = supers.count();
        Assertions.assertEquals(0, shouldBe0Again);
    }
}
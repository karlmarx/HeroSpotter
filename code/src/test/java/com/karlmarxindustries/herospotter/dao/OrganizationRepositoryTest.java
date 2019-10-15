package com.karlmarxindustries.herospotter.dao;

import com.karlmarxindustries.herospotter.dto.Organization;
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
class OrganizationRepositoryTest {
    @Autowired
    public OrganizationRepository orgs;
    
    @Test
    void testFindBySuperMembers() {
    }

    @BeforeEach
    public  void setUp() {
        orgs.deleteAll();
    }
    @Test
    public void testSaveOneGetAll() {
        Organization organization = new Organization( "The Communist Party", "commies@workers.org", "http://www.marxists.org", "+12125551212", "the best", "1 police plaza, new york, ny, 10011", "longcodeaksjdflkajshdflkahsd");
        orgs.save(organization);
        List<Organization> shouldHaveOne = orgs.findAll();
        Assertions.assertEquals(1, shouldHaveOne.size());
    }
    @Test
    public void testSaveManyGetAll() {
        Organization organization = new Organization( "The Communist Party", "commies@workers.org", "http://www.marxists.org", "+12125551212", "the best", "1 police plaza, new york, ny, 10011", "longcodeaksjdflkajshdflkahsd");
        Organization organization2 = new Organization( "The Communist Party of China", "commies@workers.cn", "http://www.marxists.cn", "+86-2125551212", "the worst", "1 police plaza, beijing", "longcodeaksjdflkajshdflkahsd");
        Organization organization3 = new Organization( "The Communist Party ofCanada", "commies@workers.ca", "http://www.marxists.ca", "+15145551212", "the best, eh", "1 police plaza, ottawa, on, canada", "sdfasdfasdfasfdasdffasdf");
        orgs.save(organization);
        orgs.save(organization2);
        orgs.save(organization3);
        List<Organization> shouldHaveThree = orgs.findAll();
        Assertions.assertEquals(3, shouldHaveThree.size());
    }
    @Test
    public void testFindByID() {
        Organization organization = new Organization( "The Communist Party", "commies@workers.org", "http://www.marxists.org", "+12125551212", "the best", "1 police plaza, new york, ny, 10011", "longcodeaksjdflkajshdflkahsd");
        Organization organization2 = new Organization( "The Communist Party of China", "commies@workers.cn", "http://www.marxists.cn", "+86-2125551212", "the worst", "1 police plaza, beijing", "longcodeaksjdflkajshdflkahsd");
        Organization organization3 = new Organization( "The Communist Party ofCanada", "commies@workers.ca", "http://www.marxists.ca", "+15145551212", "the best, eh", "1 police plaza, ottawa, on, canada", "sdfasdfasdfasfdasdffasdf");
        organization = orgs.save(organization);
        organization2 = orgs.save(organization2);
        organization3 = orgs.save(organization3);
        Organization shouldBeOrganization = orgs.findById(organization.getId()).orElse(null);
        Organization shouldBeOrganization2 = orgs.findById(organization2.getId()).orElse(null);
        Organization shouldBeOrganization3 = orgs.findById(organization3.getId()).orElse(null);
        Assertions.assertEquals(organization, shouldBeOrganization);
        Assertions.assertEquals(organization2, shouldBeOrganization2);
        Assertions.assertEquals(organization3, shouldBeOrganization3);
    }
    @Test
    public void testSavedUpdatesObject() {
        Organization organization = new Organization( "The Communist Party", "commies@workers.org", "http://www.marxists.org", "+12125551212", "the best", "1 police plaza, new york, ny, 10011", "longcodeaksjdflkajshdflkahsd");
        organization = orgs.save(organization);
        Organization shouldBe1 = orgs.findById(organization.getId()).orElse(null);
        Assertions.assertEquals(organization, shouldBe1);
        shouldBe1.setName("blech");
        orgs.save(shouldBe1);
        Organization shouldBe1Modified = orgs.findById(organization.getId()).orElse(null);
        long objectCount = orgs.count();
        Assertions.assertEquals(shouldBe1Modified, shouldBe1);
        Assertions.assertEquals(1, objectCount);
    }
    @Test
    public void testDelete() {
        Organization organization = new Organization( "The Communist Party", "commies@workers.org", "http://www.marxists.org", "+12125551212", "the best", "1 police plaza, new york, ny, 10011", "longcodeaksjdflkajshdflkahsd");
        Organization organization2 = new Organization( "The Communist Party of China", "commies@workers.cn", "http://www.marxists.cn", "+86-2125551212", "the worst", "1 police plaza, beijing", "longcodeaksjdflkajshdflkahsd");
        Organization organization3 = new Organization( "The Communist Party ofCanada", "commies@workers.ca", "http://www.marxists.ca", "+15145551212", "the best, eh", "1 police plaza, ottawa, on, canada", "sdfasdfasdfasfdasdffasdf");
        organization = orgs.save(organization);
        organization2 = orgs.save(organization2);
        organization3 = orgs.save(organization3);
        long shouldBe3 = orgs.count();
        orgs.delete(organization);
        orgs.delete(organization2);
        orgs.delete(organization3);
        long shouldBe0 = orgs.count();
        Assertions.assertEquals(0, shouldBe0);
        Assertions.assertEquals(3, shouldBe3);
        Organization organizationNew = new Organization("The Communist Party of Germany", "commies@workers.de", "http://www.marxists.de", "+445145551212", "am Besten", "1 police plaza, berlin", "asdfasdfasfasdfasdfasdf");
        organizationNew = orgs.save(organizationNew);
        orgs.deleteById(organizationNew.getId());
        long shouldBe0Again = orgs.count();
        Assertions.assertEquals(0, shouldBe0Again);
    }
}
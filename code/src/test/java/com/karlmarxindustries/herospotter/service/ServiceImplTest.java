package com.karlmarxindustries.herospotter.service;

import com.karlmarxindustries.herospotter.dto.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ServiceImplTest {
    @Autowired
    ServiceImpl service;
    @Test
    void isStringProfane() {
    }

    @Test
    void censorString() {
        String obscene = "fuck fuck fuckity fuck";
        obscene = service.censorString(obscene);
        Assertions.assertFalse(obscene.contains("fuck"));
    }

    @Test
    void generateFillerText() {
        String shouldBeFiller = service.generateFillerText();
        Assertions.assertFalse (shouldBeFiller.equals(""));
    }

    @Test
    void censorAndFillOrg() {
        Organization organization = new Organization( "The Communist Party", "fuck", "", "+12125551212", "the best", "1 police plaza, new york, ny, 10011", "longcodeaksjdflkajshdflkahsd");
        Organization censored = service.censorAndFillOrg(organization);
        Assertions.assertFalse(censored.getEmail().contains("fuck"));
        Assertions.assertFalse(censored.getUrl().equals(""));
    }

    @Test
    void censorAndFillLocation() {
    }

    @Test
    void censorAndFillPower() {
    }

    @Test
    void censorAndFillSuper() {
    }

    @Test
    void censorAndFillSighting() {
    }
}
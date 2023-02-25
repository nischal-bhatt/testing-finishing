package com.appsdeveloperblog.tutorials.junit.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserEntityIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    UserEntity u;

    @BeforeEach
    void setup()
    {
         u = new UserEntity();
        u.setUserId("serg");
        u.setFirstName("hii");
        u.setLastName("byee");
        u.setEmail("ser@ser.com");
        u.setEncryptedPassword("asdadsadad");
    }

    @Test
    void DDDDD()
    {


        UserEntity stored = testEntityManager.persistAndFlush(u);


        assertEquals(u.getFirstName(),stored.getFirstName());



    }

    @Test
    void DDDDDz()
    {

        u.setFirstName("123456789012345678901234567890abcdefghioabcdefghioabcdefghioabcdefghio");




        Assertions.assertThrows(PersistenceException.class, () -> {
            testEntityManager.persistAndFlush(u);
        });


    }
}

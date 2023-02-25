package com.appsdeveloperblog.tutorials.junit.io;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UsersRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UsersRepository usersRepository;

    @Test
    void testtesttest()
    {
        UserEntity u = new UserEntity();
        u.setFirstName("asdasd");
        u.setLastName("asdadsad");
        u.setEmail("test@test.com");
        u.setUserId("asdada");
        u.setEncryptedPassword("asdadsa");


        testEntityManager.persistAndFlush(u);


        UserEntity e=usersRepository.findByEmail(u.getEmail());

        assertEquals(u.getFirstName(),e.getFirstName());
    }
}

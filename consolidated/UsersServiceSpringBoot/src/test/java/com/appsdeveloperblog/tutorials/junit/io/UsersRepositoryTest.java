package com.appsdeveloperblog.tutorials.junit.io;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

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

    @Test
    void NNNN()
    {
        UserEntity u = new UserEntity();
        u.setFirstName("hhh");
        u.setLastName("bbb");
        u.setUserId("rrrr");
        u.setEmail("a@a.com");
        u.setEncryptedPassword("SSSS");

        testEntityManager.persistAndFlush(u);

       List<UserEntity> ls
        =usersRepository.findUsersWithEmailEndingWith("@a.com");

       assertEquals(u.getLastName(),ls.get(0).getLastName());


    }
}

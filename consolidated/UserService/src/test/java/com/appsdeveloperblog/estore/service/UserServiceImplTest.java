package com.appsdeveloperblog.estore.service;

import com.appsdeveloperblog.estore.data.UsersRepositoryImpl;
import com.appsdeveloperblog.estore.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    UsersRepositoryImpl usersRepository
            = new UsersRepositoryImpl();

    UserServiceImpl userService =
            new UserServiceImpl(null,null);

    @Test
    void callDemo() {

        userService.callDemo();

    }
}
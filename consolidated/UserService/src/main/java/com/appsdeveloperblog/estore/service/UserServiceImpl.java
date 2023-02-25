package com.appsdeveloperblog.estore.service;

import com.appsdeveloperblog.estore.data.UsersRepository;
import com.appsdeveloperblog.estore.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    EmailVerificationService emailVerificationService;

    public UserServiceImpl(UsersRepository usersRepository,
                           EmailVerificationService emailVerificationService) {
        this.usersRepository = usersRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

        if (firstName.length() == 0)
        {
            throw new IllegalArgumentException("hi nish");
        }

        if (lastName == null || lastName.trim().length() == 0)
        {
            throw new IllegalArgumentException("last name l;a");
        }
   User user = new User(firstName,lastName,email,UUID.randomUUID().toString());

        boolean result;
        try {
            result=usersRepository.save(user);
        }catch (RuntimeException e)
        {
            throw new UserServiceException("poopoo");
        }
        if (!result) throw new UserServiceException("cannot ");

        try {
            emailVerificationService.scheduleEmailConfirmation(user);
        }catch (RuntimeException e)
        {
            throw new EmailNotificationServiceException(e.getMessage());
        }
        return user;
    }

    public void callDemo()
    {
        this.demo();
    }

    private void demo()
    {
        System.out.println("hey");
    }
}

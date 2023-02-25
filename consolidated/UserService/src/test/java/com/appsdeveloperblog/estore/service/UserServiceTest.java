package com.appsdeveloperblog.estore.service;

import com.appsdeveloperblog.estore.data.UsersRepository;
import com.appsdeveloperblog.estore.model.User;
import com.appsdeveloperblog.estore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UsersRepository usersRepository;
    @Mock
    EmailVerificationServiceImpl emailVerificationService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init() {
        //userService = new UserServiceImpl();
        firstName = "Sergey";
        lastName = "Kargo";
        email = "test@test.com";
        password = "12345678";
        repeatPassword = "12345678";
    }

    @DisplayName("user object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnUserObject() {
        //Arrange
        Mockito.when(usersRepository.save(any(User.class))).thenReturn(true);

        //act

        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //assert
        assertNotNull(user);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertNotNull(user.getId());
        verify(usersRepository, times(1)).save(Mockito.any(User.class));
    }

    @DisplayName("user object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnUserObject2() {
        //Arrange
        Mockito.when(usersRepository.save(any(User.class))).thenReturn(false);

        //act

        assertThrows(UserServiceException.class, () -> {
            User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        });


        //assert

    }

    @DisplayName("Empty first name")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {


        //act
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("", lastName
                    , email, password, repeatPassword);
        });

        //assert
        assertEquals("hi nish", illegalArgumentException.getMessage());
    }

    @DisplayName("blah blah")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
        //Arrange
        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);


        //Act
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName, lastName
                    , email, password, repeatPassword);
        });
        //Assert

    }

    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException() {
        when(usersRepository.save(any(User.class))).thenReturn(true);

        doThrow(EmailNotificationServiceException.class)
                .when(emailVerificationService).scheduleEmailConfirmation
                        (any(User.class));

       // doNothing().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

        assertThrows(EmailNotificationServiceException.class,() -> {
            userService.createUser(firstName, lastName
                    , email, password, repeatPassword);
        });


    }

    @Test
    void testA()
    {
        when(usersRepository.save(any(User.class))).thenReturn(true);

        doCallRealMethod().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

        userService.createUser(firstName, lastName
                , email, password, repeatPassword);

        verify(emailVerificationService, times(1)).scheduleEmailConfirmation(Mockito.any(User.class));
    }

    @Test
    void testtestA()
    {
        //Arramge



        //Act

        assertThrows(IllegalArgumentException.class, () -> {
            User user = this.userService.createUser(firstName,"",email,password,repeatPassword);
        });


        //Assert
    }

    @Test
    void testtestB()
    {
        //Arramge



        //Act

        assertThrows(IllegalArgumentException.class, () -> {
            User user = this.userService.createUser(firstName,null,email,password,repeatPassword);
        });


        //Assert
    }


}

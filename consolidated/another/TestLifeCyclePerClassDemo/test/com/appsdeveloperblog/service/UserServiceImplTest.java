package com.appsdeveloperblog.service;

import com.appsdeveloperblog.io.UsersDatabase;
import com.appsdeveloperblog.io.UsersDatabaseMapImpl;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    UsersDatabase usersDatabase;
    UserService userService;

    String createdUserId = "";

    //this doesnt need to be static anymore
    @BeforeAll
    void setup() {
        usersDatabase = new UsersDatabaseMapImpl();

        usersDatabase.init();
        userService = new UserServiceImpl(usersDatabase);
    }

    //this doesnt need to be static anymore
    @AfterAll
    void cleanup() {
        // Close connection
        usersDatabase.close();
        // Delete database
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {

        Map<String,String> user = new HashMap<>();
        user.put("firstName","Sergey");
        user.put("lastName","Kargopolov");

         createdUserId = userService.createUser(user);

        assertNotNull(createdUserId);

    }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {

        Map<String,String> newUserDetails = new HashMap<>();
        newUserDetails.put("firstName","John");

        Map updatedUserDetails = userService.updateUser(createdUserId, newUserDetails);

        assertEquals(newUserDetails.get("firstName"),updatedUserDetails.get("firstName"));
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {

        Map userDetails = userService.getUserDetails(createdUserId);

        assertNotNull(userDetails);
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {

        userService.deleteUser(createdUserId);

        assertNull(userService.getUserDetails(createdUserId));
    }

}

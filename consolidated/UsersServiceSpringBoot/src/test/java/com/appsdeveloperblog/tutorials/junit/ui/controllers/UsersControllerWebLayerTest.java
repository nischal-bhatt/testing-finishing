package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.service.UsersService;
import com.appsdeveloperblog.tutorials.junit.shared.UserDto;
import com.appsdeveloperblog.tutorials.junit.ui.request.UserDetailsRequestModel;
import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers=UsersController.class,
excludeAutoConfiguration = {SecurityAutoConfiguration.class}) //scan for limited number of classes
//@AutoConfigureMockMvc(addFilters=false)
public class UsersControllerWebLayerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UsersService usersService;


    @Test
    @DisplayName("user can be created")
    void testCreatedUser_whenValidUserDetailsProvided_returnsCreatedUserDetails() throws Exception {

        UserDetailsRequestModel userDetailsRequestModel
                = new UserDetailsRequestModel();
        userDetailsRequestModel.setFirstName("Sert");
        userDetailsRequestModel.setLastName("Nish");
        userDetailsRequestModel.setEmail("email@email.com");
        userDetailsRequestModel.setPassword("12345678");
        userDetailsRequestModel.setRepeatPassword("12345678");

        UserDto userDto = new UserDto();
        userDto.setFirstName("Sert");
        userDto.setLastName("Nish");
        userDto.setEmail("Nish@Nish.com");
        userDto.setPassword("12345");
        userDto.setEncryptedPassword("2222");
        userDto.setUserId("heya");


        when(usersService.createUser(any(UserDto.class))).thenReturn(userDto);


        RequestBuilder requestBuilder =MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String ResponseBodyAsString = mvcResult.getResponse().getContentAsString();

       UserRest createdUser =  new ObjectMapper().readValue(ResponseBodyAsString, UserRest.class);

       assertEquals(userDetailsRequestModel.getFirstName(),
               createdUser.getFirstName());

       assertFalse(createdUser.getUserId().isEmpty());

    }

    @Test
    @DisplayName("first name is not provided")
    void aaaa() throws Exception {

        UserDetailsRequestModel userDetailsRequestModel
                = new UserDetailsRequestModel();
        userDetailsRequestModel.setFirstName("");
        userDetailsRequestModel.setLastName("Nish");
        userDetailsRequestModel.setEmail("email@email.com");
        userDetailsRequestModel.setPassword("12345678");
        userDetailsRequestModel.setRepeatPassword("12345678");

        RequestBuilder requestBuilder =MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));


        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();


        assertEquals(HttpStatus.BAD_REQUEST.value(),
                mvcResult.getResponse().getStatus()
                );
    }

}

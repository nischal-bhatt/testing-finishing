package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
        (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT /*properties="server.port=8099"*/)
@TestPropertySource(locations = "/application-test.properties")
public class UsersControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int localServerPort;

    @Test
    void contextLoads()
    {
        System.out.println(serverPort + " " + localServerPort);
    }

    @Test
    @DisplayName("if user can be created")
    void AAA() throws JSONException {
        //arrange
        JSONObject userDetails = new JSONObject();
        userDetails.put("firstName","ser");
        userDetails.put("lastName","hie");
        userDetails.put("email","t@t.com");
        userDetails.put("password","12345678");
        userDetails.put("repeatPassword","12345678");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request
                = new HttpEntity<>(userDetails.toString(),headers);



        //act
        ResponseEntity<UserRest> createdobj
        =testRestTemplate.postForEntity("/users",request,UserRest.class);

        UserRest a = createdobj.getBody();




        //assert
        Assertions.assertEquals(HttpStatus.OK, createdobj.getStatusCode());
    }

    @Test
    @DisplayName("GET /users requires jwt")
    void BBBB()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");

        HttpEntity re = new HttpEntity(null,headers);

        ResponseEntity<List<UserRest>> res = testRestTemplate.exchange("/users", HttpMethod.GET, re,
                new ParameterizedTypeReference<List<UserRest>>() {
                });

        Assertions.assertEquals(HttpStatus.FORBIDDEN,res.getStatusCode());

    }
}

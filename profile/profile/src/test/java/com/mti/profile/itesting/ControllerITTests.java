package com.mti.profile.itesting;

import com.mti.profile.controller.dto.AddProfileDtoProfileRequest;
import com.mti.profile.controller.dto.GetProfilesByKeywordsDtoRequest;
import com.mti.profile.controller.dto.GetProfilesByKeywordsDtoResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/*
 * READ THIS BEFORE STARTING TEST
 *
 * BE CAREFUL, this is INTEGRATION TESTING.
 * It will create a REAL instance of the server and start some test on your local mysql server
 * It can add/delete data randomly in your mysql server
 *
 * In order to work properly, you should have a mysql server started and all the information of connection in
 * application.properties file should be updated depending on your configuration
 *
 * */


@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerITTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    //Feature 1 Post profile
    @Test
    public void IntegrationTestPostProfile() {

        AddProfileDtoProfileRequest profile = new AddProfileDtoProfileRequest(0,
                                                                              "name",
                                                                              "firstname",
                                                                              "town",
                                                                              "title",
                                                                              "experience",
                                                                              "formation",
                                                                              "technicalSkills",
                                                                              "study");

        HttpEntity<AddProfileDtoProfileRequest> request = new HttpEntity<>(profile);

        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/profile/",
                                                                     request, String.class);

        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void IntegrationTestPostWrongProfile() {

        AddProfileDtoProfileRequest profile = new AddProfileDtoProfileRequest(0,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null);

        HttpEntity<AddProfileDtoProfileRequest> request = new HttpEntity<>(profile);

        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/profile/",
                                                                     request, String.class);

        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void IntegrationTestGetFilteredProfilesSuccess() {
        GetProfilesByKeywordsDtoRequest rq = new GetProfilesByKeywordsDtoRequest(List.of("Lamartine", "Poète"));
        HttpEntity<GetProfilesByKeywordsDtoRequest> request = new HttpEntity<>(rq);
        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/profile/filter/",
                request, String.class);
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void IntegrationTestGetFilteredProfilesFailure() {
        GetProfilesByKeywordsDtoRequest rq = new GetProfilesByKeywordsDtoRequest(new ArrayList<String>());
        HttpEntity<GetProfilesByKeywordsDtoRequest> request = new HttpEntity<>(rq);
        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/profile/filter/",
                request, String.class);
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}

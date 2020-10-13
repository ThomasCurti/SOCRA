package com.mti.profile.itesting;

import com.mti.profile.controller.dto.GetProfileByMailDtoRequest;
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

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerITEmailTests {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    //Feature 3.6 Get profiles by mail
    @Test
    public void integration_test_get_all_profiles_by_mail() {

        GetProfileByMailDtoRequest getProfileByMailDtoRequest = new GetProfileByMailDtoRequest("socraprojectapi@gmail.com");
        HttpEntity<GetProfileByMailDtoRequest> request = new HttpEntity<>(getProfileByMailDtoRequest);

        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/mail/", request, String.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody().contains("Mail sent"));
    }
}

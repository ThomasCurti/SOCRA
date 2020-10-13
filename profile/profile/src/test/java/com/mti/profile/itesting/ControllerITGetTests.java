package com.mti.profile.itesting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerITGetTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    //Feature 3.4 Get profiles
    @Test
    public void IntegrationTestGetAllProfiles() {

        ResponseEntity<String> response = restTemplate.getForEntity("http://127.0.0.1:" + port + "/profile/", String.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}

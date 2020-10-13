package com.mti.profile.itesting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerITExcelTests {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    //Feature 3.5 Get profiles as Excel
    @Test
    public void integration_test_get_all_profiles_as_excel() {

        ResponseEntity<Resource> response = restTemplate.getForEntity("http://127.0.0.1:" + port + "/excel/", Resource.class);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}

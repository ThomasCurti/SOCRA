package com.mti.profile.itesting;

import com.mti.profile.controller.dto.AddProfileDtoProfileRequest;
import com.mti.profile.controller.dto.UpdateSkillsDtoSkillsRequest;
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
public class SkillsITests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void IntegrationTestPostSkill() {

        AddProfileDtoProfileRequest profile = new AddProfileDtoProfileRequest(1,
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

        UpdateSkillsDtoSkillsRequest skill = new UpdateSkillsDtoSkillsRequest(1,
                "experience",
                "formation",
                "technicalSkills",
                "study");

        HttpEntity<AddProfileDtoProfileRequest> Skillrequest = new HttpEntity<>(profile);

        ResponseEntity<String> Skillresponse = restTemplate.postForEntity("http://127.0.0.1:" + port + "/skills/",
                request, String.class);

        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void IntegrationTestPostWrongSkill() {

        AddProfileDtoProfileRequest profile = new AddProfileDtoProfileRequest(1,
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

        UpdateSkillsDtoSkillsRequest skill = new UpdateSkillsDtoSkillsRequest(null,
                null,
                null,
                null,
                null);

        HttpEntity<AddProfileDtoProfileRequest> Skillrequest = new HttpEntity<>(profile);

        ResponseEntity<String> Skillresponse = restTemplate.postForEntity("http://127.0.0.1:" + port + "/skills/",
                request, String.class);

        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}

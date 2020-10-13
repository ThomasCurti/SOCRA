package com.mti.profile.controller;

import com.mti.profile.controller.dto.GetProfileDtoResponse;
import com.mti.profile.controller.view.ProfileController;
import com.mti.profile.domain.service.ProfileService;
import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import io.cucumber.java.nl.Stel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControllerProfileGetTest {

    @Autowired
    ProfileRepository profileRepository;

    ProfileService profileService;
    ProfileController profileController;

    @Before
    public void InitController() {
        profileRepository.deleteAll();

        profileService = new ProfileService(profileRepository);
        profileController = new ProfileController(profileService);
    }

    @AfterEach
    public void ClearDatabase() {
        profileRepository.deleteAll();
    }

    //feature 3.4
    @Test
    public void getAllProfiles() {
        //init
        ProfileModel p = new ProfileModel(0,
                                          "Test",
                                          "test",
                                          "town",
                                          "title",
                                          "experience",
                                          "formation",
                                          "technicalSkills",
                                          "study");
        ProfileModel p1 = new ProfileModel(0,
                                           "Test",
                                           "test",
                                           "town",
                                           "title",
                                           "experience",
                                           "formation",
                                           "technicalSkills",
                                           "study");
        ProfileModel p2 = new ProfileModel(0,
                                           "Test",
                                           "test",
                                           "town",
                                           "title",
                                           "experience",
                                           "formation",
                                           "technicalSkills",
                                           "study");
        ProfileModel p3 = new ProfileModel(0,
                                           "Test",
                                           "test",
                                           "town",
                                           "title",
                                           "experience",
                                           "formation",
                                           "technicalSkills",
                                           "study");

        profileRepository.saveAll(List.of(p, p1, p2, p3));

        GetProfileDtoResponse response = profileController.getAllProfiles();

        Assert.assertEquals(4, response.profiles.size());
    }

    @Test
    public void getAllProfilesSorted() {
        //init
        ProfileModel p = new ProfileModel(0,
                                          "Test2",
                                          "test2",
                                          "town",
                                          "title",
                                          "experience",
                                          "formation",
                                          "technicalSkills",
                                          "study");
        ProfileModel p1 = new ProfileModel(0,
                                           "Test2",
                                           "test",
                                           "town",
                                           "title",
                                           "experience",
                                           "formation",
                                           "technicalSkills",
                                           "study");
        ProfileModel p2 = new ProfileModel(0,
                                           "Test2",
                                           "test1",
                                           "town",
                                           "title",
                                           "experience",
                                           "formation",
                                           "technicalSkills",
                                           "study");
        ProfileModel p3 = new ProfileModel(0,
                                           "Test1",
                                           "test3",
                                           "town",
                                           "title",
                                           "experience",
                                           "formation",
                                           "technicalSkills",
                                           "study");

        profileRepository.saveAll(List.of(p, p1, p2, p3));

        GetProfileDtoResponse response = profileController.getAllProfiles();

        Assert.assertEquals(4, response.profiles.size());
        Assert.assertEquals("Test1" ,response.profiles.get(0).name);
        Assert.assertEquals("test" ,response.profiles.get(1).firstname);
        Assert.assertEquals("test1" ,response.profiles.get(2).firstname);
        Assert.assertEquals("test2" ,response.profiles.get(3).firstname);
    }
}

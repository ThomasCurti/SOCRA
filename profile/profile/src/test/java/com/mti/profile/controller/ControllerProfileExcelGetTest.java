package com.mti.profile.controller;

import com.mti.profile.controller.view.ProfileExcelController;
import com.mti.profile.domain.service.ProfileExcelService;
import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControllerProfileExcelGetTest {

    @Autowired
    ProfileRepository profileRepository;

    ProfileExcelService profileExcelService;
    ProfileExcelController profileExcelController;

    @Before
    public void InitController() {
        profileRepository.deleteAll();

        profileExcelService = new ProfileExcelService(profileRepository);
        profileExcelController = new ProfileExcelController(profileExcelService);
    }

    @AfterEach
    public void ClearDatabase() {
        profileRepository.deleteAll();
    }

    //feature 3.5
    @Test
    public void get_all_profiles_as_excel() {
        //init
        ProfileModel p1 = new ProfileModel(0,
                "Test1",
                "test1",
                "town1",
                "title1",
                "experience1",
                "formation1",
                "technicalSkills1",
                "study1");
        ProfileModel p2 = new ProfileModel(0,
                "Test2",
                "test2",
                "town2",
                "title2",
                "experience2",
                "formation2",
                "technicalSkills2",
                "study2");

        profileRepository.saveAll(List.of(p1, p2));

        ResponseEntity<Resource> response = profileExcelController.getAllProfilesAsExcel();

        Assert.assertNotNull(response);
    }
}
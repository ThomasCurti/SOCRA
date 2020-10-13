package com.mti.profile.controller;

import com.mti.profile.controller.dto.AddProfileDtoProfileRequest;
import com.mti.profile.controller.dto.GetProfilesByKeywordsDtoRequest;
import com.mti.profile.controller.dto.GetProfilesByKeywordsDtoResponse;
import com.mti.profile.controller.view.ProfileController;
import com.mti.profile.domain.service.ProfileService;
import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControllerProfileTest {

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

    //Feature 1 Post profile
    @Test
    public void insertOneProfile() {

        AddProfileDtoProfileRequest profile = new AddProfileDtoProfileRequest(0,
                                                                              "name",
                                                                              "firstname",
                                                                              "town",
                                                                              "title",
                                                                              "experience",
                                                                              "formation",
                                                                              "technicalSkills",
                                                                              "study");

        profileController.addProfile(profile);

        var test = profileRepository.findAll();
        Assert.assertNotNull(test.iterator());
        Assert.assertEquals(1, ((ArrayList) test).size());

        Assert.assertTrue(test.iterator().next() instanceof ProfileModel);
        ProfileModel p = test.iterator().next();
        Assert.assertEquals("name", p.getName());
        Assert.assertEquals("firstname", p.getFirstname());
        Assert.assertEquals("town", p.getTown());
        Assert.assertEquals("title", p.getTitle());
        Assert.assertEquals("experience", p.getExperience());
        Assert.assertEquals("formation", p.getFormation());
        Assert.assertEquals("technicalSkills", p.getTechnicalSkills());
        Assert.assertEquals("study", p.getStudy());
    }

    @Test
    public void insertWrongProfileShouldNotCrash() {

        AddProfileDtoProfileRequest profile = new AddProfileDtoProfileRequest(0,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null,
                                                                              null);

        profileController.addProfile(profile);
    }

    @Test
    public void GetFilteredProfiles5Profiles() {
        ProfileModel profile = new ProfileModel(0, "Hugo", "Victor", "Besançon", "Poète", "", "", "", "");
        ProfileModel profile2 = new ProfileModel(0, "Lamartine", "Alphonse", "Mâcon", "Poète", "", "Lamartine", "", "");
        ProfileModel profile3 = new ProfileModel(0, "Lamartine", "Victor", "Paris", "Poète", "", "", "", "");
        ProfileModel profile1 = new ProfileModel(0, "Pierre", "Paul", "Marseille", "Ecrivain", "", "", "", "");
        ProfileModel profile4 = new ProfileModel(0, "Lamartine", "Lamartine", "Lyon", "Poète", "Lamartine", "Lamartine", "Lamartine", "");
        profileRepository.saveAll(List.of(profile, profile1, profile2, profile3, profile4));

        var request = new GetProfilesByKeywordsDtoRequest(List.of("Lamartine", "Poète"));
        GetProfilesByKeywordsDtoResponse response = profileController.getProfilesByKeywords(request);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.profiles);
        Assert.assertEquals(5, response.profiles.size());
        Assert.assertEquals("Lyon", response.profiles.get(0).town);
        Assert.assertEquals("Mâcon", response.profiles.get(1).town);
        Assert.assertEquals("Paris", response.profiles.get(2).town);
        Assert.assertEquals("Besançon", response.profiles.get(3).town);
        Assert.assertEquals("Marseille", response.profiles.get(4).town);
    }

}

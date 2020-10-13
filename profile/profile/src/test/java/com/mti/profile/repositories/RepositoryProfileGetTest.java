package com.mti.profile.repositories;

import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import com.mti.profile.utils.IterableUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryProfileGetTest {

    @Autowired
    ProfileRepository profileRepository;

    @AfterEach
    public void ClearDatabase() {
        profileRepository.deleteAll();
    }

    //Feature 3.4 Get profiles
    @Test
    public void getAllProfilesShouldReturnNotNullObject() {

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

        //get everything without any sort
        //repository should not sort data since it is NOT a business layer
        var obj = profileRepository.findAll();

        Assert.assertNotNull(obj);
    }

    @Test
    public void getAllProfilesAndCastItAsList() {
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

        //get everything without any sort
        //repository should not sort data since it is NOT a business layer
        var obj = profileRepository.findAll();

        List<ProfileModel> profiles = IterableUtils.toList(obj);

        Assert.assertEquals(4, profiles.size());
    }

}

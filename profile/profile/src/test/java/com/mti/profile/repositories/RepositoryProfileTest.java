package com.mti.profile.repositories;

import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryProfileTest {

    @Autowired
    ProfileRepository profileRepository;

    @AfterEach
    public void ClearDatabase() {
        profileRepository.deleteAll();
    }

    //Feature 1 Post profile
    @Test
    public void insertOneProfile() {
        ProfileModel profile = new ProfileModel(0,
                                                "Test",
                                                "test",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");

        profileRepository.save(profile);

        var test = profileRepository.findAll();

        Assert.assertNotNull(test.iterator());
        Assert.assertEquals(1, ((ArrayList) test).size());

        Assert.assertTrue(test.iterator().next() instanceof ProfileModel);
        ProfileModel p = test.iterator().next();
        Assert.assertEquals("Test", p.getName());
        Assert.assertEquals("test", p.getFirstname());
        Assert.assertEquals("TownTest", p.getTown());
        Assert.assertEquals("TitleTest", p.getTitle());
        Assert.assertEquals("No experience", p.getExperience());
        Assert.assertEquals("No formation", p.getFormation());
        Assert.assertEquals("No skills", p.getTechnicalSkills());
        Assert.assertEquals("No study", p.getStudy());
    }

    @Test
    public void insertSeveralsProfile() {
        ProfileModel profile = new ProfileModel(0,
                                                "Test",
                                                "test",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");

        ProfileModel profile2 = new ProfileModel(0,
                                                "Test2",
                                                "test2",
                                                "TownTest2",
                                                "TitleTest2",
                                                "No experience2",
                                                "No formation2",
                                                "No skills2",
                                                "No study2");

        List<ProfileModel> profiles = List.of(profile, profile2);
        profileRepository.saveAll(profiles);

        var test = profileRepository.findAll();

        Assert.assertNotNull(test.iterator());
        Assert.assertEquals(2, ((ArrayList) test).size());

        ProfileModel p1 = (ProfileModel) ((ArrayList) test).get(0);
        Assert.assertEquals("Test", p1.getName());
        Assert.assertEquals("test", p1.getFirstname());
        Assert.assertEquals("TownTest", p1.getTown());
        Assert.assertEquals("TitleTest", p1.getTitle());
        Assert.assertEquals("No experience", p1.getExperience());
        Assert.assertEquals("No formation", p1.getFormation());
        Assert.assertEquals("No skills", p1.getTechnicalSkills());
        Assert.assertEquals("No study", p1.getStudy());

        ProfileModel p2 = (ProfileModel) ((ArrayList) test).get(1);
        Assert.assertEquals("Test2", p2.getName());
        Assert.assertEquals("test2", p2.getFirstname());
        Assert.assertEquals("TownTest2", p2.getTown());
        Assert.assertEquals("TitleTest2", p2.getTitle());
        Assert.assertEquals("No experience2", p2.getExperience());
        Assert.assertEquals("No formation2", p2.getFormation());
        Assert.assertEquals("No skills2", p2.getTechnicalSkills());
        Assert.assertEquals("No study2", p2.getStudy());
    }

}

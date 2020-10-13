package com.mti.profile.services;

import com.mti.profile.domain.entity.ProfileEntity;
import com.mti.profile.domain.entity.SkillsEntity;
import com.mti.profile.domain.service.ProfileService;
import com.mti.profile.domain.service.SkillsService;
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

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceSkillsTest {
    @Autowired
    ProfileRepository repositoryProfile;

    ProfileService profileService;
    SkillsService skillsService;

    @Before
    public void Init() {
        repositoryProfile.deleteAll();
        profileService = new ProfileService(repositoryProfile);
        skillsService = new SkillsService(repositoryProfile);
    }

    @AfterEach
    public void Clear() {
        repositoryProfile.deleteAll();
    }

    //Feature 1 Post profile
    @Test
    public void UpdateOneProfile() {
        ProfileEntity profile = new ProfileEntity(0,
                "Test",
                "test",
                "TownTest",
                "TitleTest",
                "No experience",
                "No formation",
                "No skills",
                "No study");

        ProfileEntity res = profileService.AddProfile(profile);

        var test = repositoryProfile.findAll();
        Assert.assertNotNull(test.iterator());
        Assert.assertEquals(1, ((ArrayList) test).size());

        SkillsEntity skillUpdate = new SkillsEntity(res.id,
                "Update experience",
                "Update formation",
                "Update skills",
                "Update study");

        skillsService.UpdateSkills(skillUpdate);

        var testUpdate = repositoryProfile.findAll();
        Assert.assertNotNull(testUpdate.iterator());
        Assert.assertEquals(1, ((ArrayList) testUpdate).size());
        Assert.assertTrue(testUpdate.iterator().next() instanceof ProfileModel);
        ProfileModel p = testUpdate.iterator().next();
        Assert.assertEquals("Test", p.getName());
        Assert.assertEquals("test", p.getFirstname());
        Assert.assertEquals("TownTest", p.getTown());
        Assert.assertEquals("TitleTest", p.getTitle());
        Assert.assertEquals("Update experience", p.getExperience());
        Assert.assertEquals("Update formation", p.getFormation());
        Assert.assertEquals("Update skills", p.getTechnicalSkills());
        Assert.assertEquals("Update study", p.getStudy());
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateSkillWithWrongProfile() {
        ProfileEntity profile = new ProfileEntity(0,
                "Test",
                "test",
                "TownTest",
                "TitleTest",
                "No experience",
                "No formation",
                "No skills",
                "No study");

        ProfileEntity res = profileService.AddProfile(profile);

        var test = repositoryProfile.findAll();
        Assert.assertNotNull(test.iterator());
        Assert.assertEquals(1, ((ArrayList) test).size());

        SkillsEntity skillUpdate = new SkillsEntity(res.id + 1,
                "Update experience",
                "Update formation",
                "Update skills",
                "Update study");

        skillsService.UpdateSkills(skillUpdate);
    }
}

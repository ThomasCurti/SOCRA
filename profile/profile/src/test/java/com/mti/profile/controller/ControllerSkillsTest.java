package com.mti.profile.controller;

import com.mti.profile.controller.dto.UpdateSkillsDtoSkillsRequest;
import com.mti.profile.controller.view.ProfileController;
import com.mti.profile.controller.view.SkillsController;
import com.mti.profile.domain.entity.ProfileEntity;
import com.mti.profile.domain.service.ProfileService;
import com.mti.profile.domain.service.SkillsService;
import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import com.mti.profile.utils.IterableUtils;
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
public class ControllerSkillsTest {

    @Autowired
    ProfileRepository profileRepository;

    ProfileService profileService;
    ProfileController profileController;

    SkillsService skillsService;
    SkillsController skillsController;

    @Before
    public void InitController() {
        profileRepository.deleteAll();

        profileService = new ProfileService(profileRepository);
        profileController = new ProfileController(profileService);

        skillsService = new SkillsService(profileRepository);
        skillsController = new SkillsController(skillsService);
    }

    @AfterEach
    public void ClearDatabase() {
        profileRepository.deleteAll();
    }

    @Test
    public void UpdateOneSkills() {

        ProfileEntity profile = new ProfileEntity(0,
                "name",
                "firstname",
                "town",
                "title",
                "No experience",
                "No formation",
                "No skills",
                "No study");

        ProfileEntity res = profileService.AddProfile(profile);
        Assert.assertNotNull(res);

        UpdateSkillsDtoSkillsRequest skillUpdateRequest = new UpdateSkillsDtoSkillsRequest(res.id,
                "experience Update",
                "formation Update",
                "technicalSkills Update",
                "study Update");

        skillsController.UpdateSkills(skillUpdateRequest);

        var test = profileRepository.findAll();
        Assert.assertNotNull(test.iterator());
        Assert.assertEquals(1, IterableUtils.toList(test).size());

        Assert.assertTrue(test.iterator().next() instanceof ProfileModel);
        ProfileModel p = test.iterator().next();
        Assert.assertEquals("name", p.getName());
        Assert.assertEquals("firstname", p.getFirstname());
        Assert.assertEquals("town", p.getTown());
        Assert.assertEquals("title", p.getTitle());
        Assert.assertEquals("experience Update", p.getExperience());
        Assert.assertEquals("formation Update", p.getFormation());
        Assert.assertEquals("technicalSkills Update", p.getTechnicalSkills());
        Assert.assertEquals("study Update", p.getStudy());
    }

    @Test
    public void UpdateWrongSkills() {

        ProfileEntity profile = new ProfileEntity(0,
                "name",
                "firstname",
                "town",
                "title",
                "No experience",
                "No formation",
                "No skills",
                "No study");

        ProfileEntity res = profileService.AddProfile(profile);

        UpdateSkillsDtoSkillsRequest skillUpdateRequest = new UpdateSkillsDtoSkillsRequest(null,
                "experience Update",
                "formation Update",
                "technicalSkills Update",
                "study Update");

         skillsController.UpdateSkills(skillUpdateRequest);
    }
}

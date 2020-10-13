package com.mti.profile.services;

import com.mti.profile.domain.entity.ProfileEntity;
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
public class ServiceProfileTest {

    @Autowired
    ProfileRepository repositoryProfile;

    ProfileService profileService;

    @Before
    public void Init() {
        repositoryProfile.deleteAll();
        profileService = new ProfileService(repositoryProfile);
    }

    @AfterEach
    public void Clear() {
        repositoryProfile.deleteAll();
    }

    //Feature 1 Post profile
    @Test
    public void insertOneProfile() {
        ProfileEntity profile = new ProfileEntity(0,
                                                  "Test",
                                                  "test",
                                                  "TownTest",
                                                  "TitleTest",
                                                  "No experience",
                                                  "No formation",
                                                  "No skills",
                                                  "No study");

        profileService.AddProfile(profile);

        var test = repositoryProfile.findAll();
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

    @Test(expected = IllegalArgumentException.class)
    public void insertWrongProfileShouldThrowException() {
        ProfileEntity profile = new ProfileEntity(0, null, null, null, null, null, null, null, null);
        profileService.AddProfile(profile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProfilesByKeywordsNoKeyword(){
        profileService.getProfilesByKeywords(new ArrayList<String>());
    }

    @Test
    public void getAllProfilesByKeywordEmpty(){
        List<ProfileEntity> profiles = profileService.getProfilesByKeywords(List.of("test"));
        Assert.assertNotNull(profiles);
        Assert.assertEquals(0, profiles.size());
    }

    @Test
    public void getAllProfilesByKeywordOneProfile(){
        ProfileModel profile = new ProfileModel(0, "Hugo", "Victor", "Besançon", "Poète", "", "", "", "");
        repositoryProfile.save(profile);
        List<ProfileEntity> profiles = profileService.getProfilesByKeywords(List.of("test"));
        Assert.assertNotNull(profiles);
        Assert.assertEquals(1, profiles.size());
    }

    @Test
    public void getAllProfilesByKeywordTwoProfilesDefaultSorted(){
        ProfileModel profile = new ProfileModel(0, "Hugo", "Victor", "Besançon", "Poète", "", "", "", "");
        ProfileModel profile2 = new ProfileModel(0, "Lamartine", "Alphonse", "Mâcon", "Poète", "", "", "", "");
        repositoryProfile.saveAll(List.of(profile, profile2));
        List<ProfileEntity> profiles = profileService.getProfilesByKeywords(List.of("Hugo"));
        Assert.assertNotNull(profiles);
        Assert.assertEquals(2, profiles.size());
        Assert.assertEquals("Besançon", profiles.get(0).town);
        Assert.assertEquals("Mâcon", profiles.get(1).town);
    }

    @Test
    public void getAllProfilesByKeywordTwoProfilesDefaultNonSorted(){
        ProfileModel profile = new ProfileModel(0, "Hugo", "Victor", "Besançon", "Poète", "", "", "", "");
        ProfileModel profile2 = new ProfileModel(0, "Lamartine", "Alphonse", "Mâcon", "Poète", "", "", "", "");
        repositoryProfile.saveAll(List.of(profile, profile2));
        List<ProfileEntity> profiles = profileService.getProfilesByKeywords(List.of("Mâcon"));
        Assert.assertNotNull(profiles);
        Assert.assertEquals(2, profiles.size());
        Assert.assertEquals("Mâcon", profiles.get(0).town);
        Assert.assertEquals("Besançon", profiles.get(1).town);
    }

    @Test
    public void getAllProfilesByKeywordThreeProfiles(){
        ProfileModel profile = new ProfileModel(0, "Hugo", "Victor", "Besançon", "Poète", "", "", "", "");
        ProfileModel profile2 = new ProfileModel(0, "Lamartine", "Alphonse", "Mâcon", "Poète", "", "", "", "");
        ProfileModel profile3 = new ProfileModel(0, "Lamartine", "Victor", "Paris", "Poète", "", "", "", "");
        ProfileModel profile1 = new ProfileModel(0, "Pierre", "Paul", "Marseille", "Ecrivain", "", "", "", "");
        repositoryProfile.saveAll(List.of(profile, profile1, profile3, profile2));
        List<ProfileEntity> profiles = profileService.getProfilesByKeywords(List.of("Lamartine", "Poète", "Victor"));
        Assert.assertNotNull(profiles);
        Assert.assertEquals(4, profiles.size());
        Assert.assertEquals("Paris", profiles.get(0).town);
        Assert.assertEquals("Marseille", profiles.get(3).town);
    }
}

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

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceProfileGetTest {
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

    //Feature 3.4 Get profiles
    @Test
    public void getOneProfile() {
        ProfileModel profile = new ProfileModel(0,
                                                "Test",
                                                "test",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");
        repositoryProfile.save(profile);

        List<ProfileEntity> profiles = profileService.getAllProfiles();

        Assert.assertEquals(1, profiles.size());
    }

    @Test
    public void getAllProfiles() {
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
                                                "Test",
                                                "test",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");
        ProfileModel profile3 = new ProfileModel(0,
                                                "Test",
                                                "test",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");
        ProfileModel profile4 = new ProfileModel(0,
                                                "Test",
                                                "test",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");

        repositoryProfile.saveAll(List.of(profile, profile2, profile3, profile4));

        List<ProfileEntity> profiles = profileService.getAllProfiles();

        Assert.assertEquals(4, profiles.size());
    }

    @Test
    public void getAllProfilesThenSortByName() {
        ProfileModel profile = new ProfileModel(0,
                                                "Test4",
                                                "test",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");
        ProfileModel profile2 = new ProfileModel(0,
                                                 "Test1",
                                                 "test",
                                                 "TownTest",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile3 = new ProfileModel(0,
                                                 "Test2",
                                                 "test",
                                                 "TownTest",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile4 = new ProfileModel(0,
                                                 "Test3",
                                                 "test",
                                                 "TownTest",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        repositoryProfile.saveAll(List.of(profile, profile2, profile3, profile4));

        List<ProfileEntity> profiles = profileService.getAllProfiles();

        Assert.assertEquals(4, profiles.size());
        Assert.assertEquals("Test1", profiles.get(0).name);
        Assert.assertEquals("Test2", profiles.get(1).name);
        Assert.assertEquals("Test3", profiles.get(2).name);
        Assert.assertEquals("Test4", profiles.get(3).name);
    }

    @Test
    public void getAllProfilesThenSortByFirstName() {
        ProfileModel profile = new ProfileModel(0,
                                                "Test",
                                                "test1",
                                                "TownTest",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");
        ProfileModel profile2 = new ProfileModel(0,
                                                 "Test",
                                                 "test4",
                                                 "TownTest",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile3 = new ProfileModel(0,
                                                 "Test",
                                                 "test2",
                                                 "TownTest",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile4 = new ProfileModel(0,
                                                 "Test",
                                                 "test3",
                                                 "TownTest",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        repositoryProfile.saveAll(List.of(profile, profile2, profile3, profile4));

        List<ProfileEntity> profiles = profileService.getAllProfiles();

        Assert.assertEquals(4, profiles.size());
        Assert.assertEquals("test1", profiles.get(0).firstname);
        Assert.assertEquals("test2", profiles.get(1).firstname);
        Assert.assertEquals("test3", profiles.get(2).firstname);
        Assert.assertEquals("test4", profiles.get(3).firstname);
    }

    @Test
    public void getAllProfilesThenSortByTown() {
        ProfileModel profile = new ProfileModel(0,
                                                "Test",
                                                "test",
                                                "TownTest4",
                                                "TitleTest",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");
        ProfileModel profile2 = new ProfileModel(0,
                                                 "Test",
                                                 "test",
                                                 "TownTest2",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile3 = new ProfileModel(0,
                                                 "Test",
                                                 "test",
                                                 "TownTest3",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile4 = new ProfileModel(0,
                                                 "Test",
                                                 "test",
                                                 "TownTest1",
                                                 "TitleTest",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        repositoryProfile.saveAll(List.of(profile, profile2, profile3, profile4));

        List<ProfileEntity> profiles = profileService.getAllProfiles();

        Assert.assertEquals(4, profiles.size());
        Assert.assertEquals("TownTest1", profiles.get(0).town);
        Assert.assertEquals("TownTest2", profiles.get(1).town);
        Assert.assertEquals("TownTest3", profiles.get(2).town);
        Assert.assertEquals("TownTest4", profiles.get(3).town);
    }


    @Test
    public void getAllProfilesThenSortByTitle() {
        ProfileModel profile = new ProfileModel(0,
                                                "Test",
                                                "test",
                                                "TownTest",
                                                "TitleTest4",
                                                "No experience",
                                                "No formation",
                                                "No skills",
                                                "No study");
        ProfileModel profile2 = new ProfileModel(0,
                                                 "Test",
                                                 "test",
                                                 "TownTest",
                                                 "TitleTest2",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile3 = new ProfileModel(0,
                                                 "Test",
                                                 "test",
                                                 "TownTest",
                                                 "TitleTest3",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        ProfileModel profile4 = new ProfileModel(0,
                                                 "Test",
                                                 "test",
                                                 "TownTest",
                                                 "TitleTest1",
                                                 "No experience",
                                                 "No formation",
                                                 "No skills",
                                                 "No study");
        repositoryProfile.saveAll(List.of(profile, profile2, profile3, profile4));

        List<ProfileEntity> profiles = profileService.getAllProfiles();

        Assert.assertEquals(4, profiles.size());
        Assert.assertEquals("TitleTest1", profiles.get(0).title);
        Assert.assertEquals("TitleTest2", profiles.get(1).title);
        Assert.assertEquals("TitleTest3", profiles.get(2).title);
        Assert.assertEquals("TitleTest4", profiles.get(3).title);
    }
}

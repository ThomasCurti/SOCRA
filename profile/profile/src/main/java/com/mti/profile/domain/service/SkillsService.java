package com.mti.profile.domain.service;

import com.mti.profile.domain.entity.ProfileEntity;
import com.mti.profile.domain.entity.SkillsEntity;
import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import com.mti.profile.utils.IterableUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillsService {
    private final ProfileRepository profileRepository;

    /**
     * Constructor
     * @param profileRepository repository injected
     * */
    public SkillsService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Add a profile
     * @param skillsEntity skills that should be updated
     * @return ProfileEntity the profile updated
     * */
    public ProfileEntity UpdateSkills(SkillsEntity skillsEntity)
    {
        if(skillsEntity.profil_id == null || skillsEntity.experience == null || skillsEntity.formation == null || skillsEntity.technical_skills == null || skillsEntity.study == null)
        {
            throw new IllegalArgumentException();
        }
        var listProfile = IterableUtils.toList(profileRepository.findAll());
        List<ProfileModel> profileModelsList = listProfile.stream().filter(entity -> new Integer(entity.getId()).equals(skillsEntity.profil_id)).collect(Collectors.toList());
        if(profileModelsList.size() == 0)
        {
            throw new IllegalArgumentException();
        }
        ProfileModel profileModel = profileModelsList.get(0);

        profileModel.setExperience(skillsEntity.experience);
        profileModel.setFormation(skillsEntity.formation);
        profileModel.setTechnicalSkills(skillsEntity.technical_skills);
        profileModel.setStudy(skillsEntity.study);

        profileRepository.save(profileModel);
        ProfileEntity profileEntity = new ProfileEntity(profileModel.getId(),
                profileModel.getName(),
                profileModel.getFirstname(),
                profileModel.getTown(),
                profileModel.getTitle(),
                profileModel.getExperience(),
                profileModel.getFormation(),
                profileModel.getTechnicalSkills(),
                profileModel.getStudy());

        return profileEntity;
    }
}

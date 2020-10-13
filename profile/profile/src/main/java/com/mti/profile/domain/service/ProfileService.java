package com.mti.profile.domain.service;

import com.mti.profile.domain.entity.ProfileEntity;
import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import com.mti.profile.utils.IterableUtils;
import com.mti.profile.utils.Pair;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    /**
     * Constructor
     * @param profileRepository repository injected
     * */
    public ProfileService(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * Add a profile
     * @param profile profile that should be added
     * @return ProfileEntity the profile added
     * */
    public ProfileEntity AddProfile(ProfileEntity profile) {

        if (profile.name == null || profile.firstname == null || profile.town == null || profile.title == null || profile.experience == null || profile.formation == null || profile.technicalSkills == null || profile.study == null)
            throw new IllegalArgumentException();

        ProfileModel p = new ProfileModel(profile.id,
                                          profile.name,
                                          profile.firstname,
                                          profile.town,
                                          profile.title,
                                          profile.experience,
                                          profile.formation,
                                          profile.technicalSkills,
                                          profile.study);

        var profileModel = profileRepository.save(p);
        var profileEntity = new ProfileEntity(profileModel.getId(),
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

    /**
     * Get all profiles and sort them all
     * @return List<ProfileEntity> which are all profiles entities sorted
     * */
    public List<ProfileEntity> getAllProfiles() {
        var list = IterableUtils.toList(profileRepository.findAll());
        var listService = list.stream()
                .sorted()
                .map(entity -> new ProfileEntity(entity.getId(),
                        entity.getName(),
                        entity.getFirstname(),
                        entity.getTown(),
                        entity.getTitle(),
                        entity.getExperience(),
                        entity.getFormation(),
                        entity.getTechnicalSkills(),
                        entity.getStudy()))
                .collect(
                        Collectors.toList());
        return listService;
    }

    /**
     * This method counts the number of occurence of a substring in a string
     *
     * @param str The string to examine
     * @param findStr The string to find int str
     */
    private static Integer countOccurrences(String str, String findStr)
    {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }

    /**
     * This method computes the pertinence of one profile according to the keywords in argument
     *
     * @param profile The profile which pertinence must be calculated
     * @param keywords The list of keywords
     */
    private static Float computeOccurrenceScore(ProfileEntity profile, List<String> keywords)
    {
        Float result = 0f;
        int n = keywords.size();
        int m = 0;
        for (String kw : keywords)
        {
            Float loopStartScore = result;
            result += countOccurrences(profile.name, kw);
            result += countOccurrences(profile.firstname, kw);
            result += countOccurrences(profile.town, kw);
            result += countOccurrences(profile.title, kw);
            result += countOccurrences(profile.experience, kw);
            result += countOccurrences(profile.formation, kw);
            result += countOccurrences(profile.technicalSkills, kw);
            result += countOccurrences(profile.study, kw);

            if (loopStartScore > result)
                m++;
        }
        return m * result - 0.5f * n * result;
    }

    /**
     * This method returns all profiles ordered by pertinence according to the keywords in argument
     *
     * @param keywords The list of keywords
     */
    public List<ProfileEntity> getProfilesByKeywords(List<String> keywords) {
        if (keywords == null || keywords.size() == 0)
            throw new IllegalArgumentException();
        var list = IterableUtils.toList(profileRepository.findAll());
        List<ProfileEntity> profiles = list.stream()
                .map(model -> new ProfileEntity(model.getId(), model.getName(), model.getFirstname(),
                        model.getTown(), model.getTitle(), model.getExperience(),
                        model.getFormation(), model.getTechnicalSkills(), model.getStudy()))
                .collect(Collectors.toList());
        List<Pair<ProfileEntity, Float>> temp = new ArrayList<>();
        for (ProfileEntity profile : profiles) {
            Float score = computeOccurrenceScore(profile, keywords);
            temp.add(new Pair<>(profile, score));
        }
        temp.sort(Comparator.comparing(Pair::getSecond));
        return temp.stream()
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }
}

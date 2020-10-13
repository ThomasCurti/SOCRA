package com.mti.profile.controller.view;

import com.mti.profile.controller.dto.AddProfileDtoProfileRequest;
import com.mti.profile.controller.dto.AddProfileDtoProfileResponse;
import com.mti.profile.controller.dto.GetProfileDtoResponse;
import com.mti.profile.controller.dto.GetProfilesByKeywordsDtoRequest;
import com.mti.profile.controller.dto.GetProfilesByKeywordsDtoResponse;
import com.mti.profile.domain.entity.ProfileEntity;
import com.mti.profile.domain.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    /**
     * Constructor
     * @param profileService service injected
     * */
    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Add a profile
     * @param request request that is received @see AddProfileDtoProfileRequest
     * @return AddProfileDtoProfileResponse response that is sent to the user @see AddProfileDtoProfileResponse
     * */
    @PostMapping("/")
    public AddProfileDtoProfileResponse addProfile(@RequestBody final AddProfileDtoProfileRequest request) {

        final ProfileEntity entity = new ProfileEntity(request.id,
                                                       request.name,
                                                       request.firstname,
                                                       request.town,
                                                       request.title,
                                                       request.experience,
                                                       request.formation,
                                                       request.technicalSkills,
                                                       request.study);

        ProfileEntity result = null;
        try {
            result = profileService.AddProfile(entity);
        } catch (Exception e) {
            return null;
        }
        return new AddProfileDtoProfileResponse(result.id,
                                                result.name,
                                                result.firstname,
                                                result.town,
                                                result.title,
                                                result.experience,
                                                result.formation,
                                                result.technicalSkills,
                                                result.study);
    }

    /**
     * Return all profiles sorted
     * @return GetProfileDtoResponse response that is sent to the user @see GetProfileDtoResponse
     * */
    @GetMapping("/")
    public GetProfileDtoResponse getAllProfiles() {
        var serviceResponse = profileService.getAllProfiles();
        var responseObjects = serviceResponse.stream()
                .map(entity -> new GetProfileDtoResponse.GetProfileDto(
                        entity.id,
                        entity.name,
                        entity.firstname,
                        entity.town,
                        entity.title,
                        entity.experience,
                        entity.formation,
                        entity.technicalSkills,
                        entity.study))
                .collect(Collectors.toList());
        var response = new GetProfileDtoResponse(responseObjects);
        return response;
    }

    /**
     * Search profile and filter them using keywords
     * @param request request that is received @see GetProfilesByKeywordsDtoRequest
     * @return GetProfilesByKeywordsDtoResponse response that is sent to the user @see GetProfilesByKeywordsDtoResponse
     * */
    @PostMapping("/filter/")
    public GetProfilesByKeywordsDtoResponse getProfilesByKeywords(@RequestBody GetProfilesByKeywordsDtoRequest request){
        try {
            final List<ProfileEntity> serviceResult = profileService.getProfilesByKeywords(request.keywords);

            var profilesList = serviceResult.stream()
                    .map(entity -> new GetProfilesByKeywordsDtoResponse.GetProfileDtoResponse(
                            entity.id,
                            entity.name,
                            entity.firstname,
                            entity.town,
                            entity.title,
                            entity.experience,
                            entity.formation,
                            entity.technicalSkills,
                            entity.study
                    ))
                    .collect(Collectors.toList());

            return new GetProfilesByKeywordsDtoResponse(profilesList, null);
        }
        catch (IllegalArgumentException e)
        {
            return new GetProfilesByKeywordsDtoResponse(null, "Bad Request");
        }
    }
}

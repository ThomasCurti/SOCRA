package com.mti.profile.controller.view;

import com.mti.profile.controller.dto.UpdateSkillsDtoSkillsRequest;
import com.mti.profile.controller.dto.UpdateSkillsDtoSkillsResponse;
import com.mti.profile.domain.entity.ProfileEntity;
import com.mti.profile.domain.entity.SkillsEntity;
import com.mti.profile.domain.service.SkillsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
public class SkillsController {
    private final SkillsService skillsService;

    /**
     * Constructor
     * @param skillsService service injected
     * */
    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    /**
     * Add a profile
     * @param request request that is received @see UpdateSkillsDtoSkillsRequest
     * @return UpdateSkillsDtoSkillsResponse response that is sent to the user @see UpdateSkillsDtoSkillsResponse
     * */
    @PostMapping("/")
    public UpdateSkillsDtoSkillsResponse UpdateSkills(@RequestBody final UpdateSkillsDtoSkillsRequest request) {

        if(request.id == null || request.experience == null || request.formation == null || request.technicalSkills == null || request.study == null)
        {
            return new UpdateSkillsDtoSkillsResponse(-1, "error", null, null, null, null, null, null, null);
        }
        final SkillsEntity entity = new SkillsEntity(request.id,request.experience, request.formation, request.technicalSkills, request.study);

        ProfileEntity result = null;
        try {
            result = skillsService.UpdateSkills(entity);
        } catch (Exception e) {
            return new UpdateSkillsDtoSkillsResponse(-1, "error", null, null, null, null, null, null, null);
        }
        return new UpdateSkillsDtoSkillsResponse(result.id,
                                                 result.name,
                                                 result.firstname,
                                                 result.town,
                                                 result.title,
                                                 result.experience,
                                                 result.formation,
                                                 result.technicalSkills,
                                                 result.study);
    }
}

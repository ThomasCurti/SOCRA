package com.mti.profile.controller.dto;

/**
 * <b>UpdateSkillsDtoSkillsRequest</b>
 * Describe elements that should be present when user make request to update skills
 * */
public class UpdateSkillsDtoSkillsRequest {
    public final Integer id;
    public final String experience;
    public final String formation;
    public final String technicalSkills;
    public final String study;


    public UpdateSkillsDtoSkillsRequest(Integer id, String experience, String formation, String technicalSkills, String study) {
        this.id = id;
        this.experience = experience;
        this.formation = formation;
        this.technicalSkills = technicalSkills;
        this.study = study;
    }
}

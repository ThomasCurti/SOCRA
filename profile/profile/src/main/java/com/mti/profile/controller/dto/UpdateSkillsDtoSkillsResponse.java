package com.mti.profile.controller.dto;

/**
 * <b>UpdateSkillsFtoSkillsResponse</b>
 * Describe elements that should be present in the response when the user asks to update skills
 * */
public class UpdateSkillsDtoSkillsResponse {
    public final Integer id;
    public final String name;
    public final String firstname;
    public final String town;
    public final String title;
    public final String experience;
    public final String formation;
    public final String technicalSkills;
    public final String study;

    public UpdateSkillsDtoSkillsResponse(Integer id, String name, String firstname, String town, String title, String experience, String formation, String technicalSkills, String study) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.town = town;
        this.title = title;
        this.experience = experience;
        this.formation = formation;
        this.technicalSkills = technicalSkills;
        this.study = study;
    }
}

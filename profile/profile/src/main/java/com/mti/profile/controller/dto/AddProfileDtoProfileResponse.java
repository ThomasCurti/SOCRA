package com.mti.profile.controller.dto;

/**
 * <b>AddProfileDtoProfileResponse</b>
 * Describe elements that should present in the response when the user asks to add profile
 * */
public class AddProfileDtoProfileResponse {
    public final Integer id;
    public final String name;
    public final String firstname;
    public final String town;
    public final String title;
    public final String experience;
    public final String formation;
    public final String technicalSkills;
    public final String study;

    public AddProfileDtoProfileResponse(final Integer id, final String name,
                                       final String firstname, final String town,
                                       final String title, final String experience,
                                       final String formation, final String technicalSkills,
                                       final String study) {
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

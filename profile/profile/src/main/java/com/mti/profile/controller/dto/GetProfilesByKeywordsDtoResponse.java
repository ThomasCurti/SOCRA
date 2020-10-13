package com.mti.profile.controller.dto;

import java.util.List;

/**
 * <b>GetProfilesByKeywordsDtoResponse</b>
 * Describe elements that should present in the response when the user asks to search for profile
 * */
public class GetProfilesByKeywordsDtoResponse {

    public final List<GetProfileDtoResponse> profiles;
    public final String error;

    public GetProfilesByKeywordsDtoResponse(final List<GetProfileDtoResponse> profiles, final String error) {
        this.profiles = profiles;
        this.error = error;
    }

    public static class GetProfileDtoResponse {
        public final Integer id;
        public final String name;
        public final String firstname;
        public final String town;
        public final String title;
        public final String experience;
        public final String formation;
        public final String technicalSkills;
        public final String study;

        public GetProfileDtoResponse(final Integer id, final String name,
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
}

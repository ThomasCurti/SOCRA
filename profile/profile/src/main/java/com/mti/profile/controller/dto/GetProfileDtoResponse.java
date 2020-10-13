package com.mti.profile.controller.dto;

import java.util.List;

/**
 * <b>GetProfileDtoResponse</b>
 * Describe elements that should present in the response when the user asks to get all profiles
 * NB: it implements comparator to make sure that the data can be sorted
 * */
public class GetProfileDtoResponse {

    public List<GetProfileDto> profiles;

    public GetProfileDtoResponse(final List<GetProfileDto> profiles) {
        this.profiles = profiles;
    }

    public static class GetProfileDto implements Comparable  {
        public final Integer id;
        public final String name;
        public final String firstname;
        public final String town;
        public final String title;
        public final String experience;
        public final String formation;
        public final String technicalSkills;
        public final String study;

        public GetProfileDto(final Integer id,
                             final String name,
                             final String firstname,
                             final String town,
                             final String title,
                             final String experience,
                             final String formation, final String technicalSkills, final String study) {
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

        @Override
        public int compareTo(final Object o) {
            GetProfileDto profile = (GetProfileDto) o;
            int nameCompare = name.compareTo(profile.name);
            if (nameCompare != 0) {
                return nameCompare;
            }

            int firstnameCompare = firstname.compareTo(profile.firstname);
            if (firstnameCompare != 0) {
                return firstnameCompare;
            }

            int townCompare = town.compareTo(profile.town);
            if (townCompare != 0) {
                return townCompare;
            }

            return title.compareTo(profile.title);
        }
    }
}

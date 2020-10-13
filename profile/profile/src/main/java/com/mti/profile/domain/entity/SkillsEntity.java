package com.mti.profile.domain.entity;

/**
 * <b>SkillsEntity</b>
 * Describe elements that should be used when handling skills in the business layer
 * */
public class SkillsEntity {
    public final Integer profil_id;
    public final String experience;
    public final String formation;
    public final String technical_skills;
    public final String study;

    public SkillsEntity(Integer profil_id, String experience, String formation, String technical_skills, String study) {
        this.profil_id = profil_id;
        this.experience = experience;
        this.formation = formation;
        this.technical_skills = technical_skills;
        this.study = study;
    }
}

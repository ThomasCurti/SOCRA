package com.mti.profile.persistence.model;

import javax.persistence.*;

/**
 * <b>ProfileModel</b>
 * Database model of profile
 *
 * Use JPA & Hibernate
 * Implements Comparable in order to be sorted if needed
 * */
@Entity
@Table(name="profile")
public class ProfileModel implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @Column(name="firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name="town", nullable = false, length = 128)
    private String town;

    @Column(name="title", nullable = false, length = 128)
    private String title;

    @Column(name="experience", nullable = false, length = 255)
    private String experience;

    @Column(name="formation", nullable = false, length = 255)
    private String formation;

    @Column(name="technical_skills", nullable = false, length = 255)
    private String technicalSkills;

    @Column(name="study", nullable = false, length = 255)
    private String study;

    public ProfileModel(final Integer id, final String name, final String firstname, final String town,
                        final String title, final String experience, final String formation,
                        final String technicalSkills, final String study) {
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

    public ProfileModel(){ }

    /**
     * Get the value of id.
     *
     * @return The value of id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the value id field.
     *
     * @param id The value to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the value of name.
     *
     * @return The value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value name field.
     *
     * @param name The value to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of firstname.
     *
     * @return The value of firstname.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the value firstname field.
     *
     * @param firstname The value to set.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Get the value of town.
     *
     * @return The value of town.
     */
    public String getTown() {
        return town;
    }

    /**
     * Set the value town field.
     *
     * @param town The value to set.
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Get the value of title.
     *
     * @return The value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value title field.
     *
     * @param title The value to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the value of experience.
     *
     * @return The value of experience.
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Set the value experience field.
     *
     * @param experience The value to set.
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * Get the value of formation.
     *
     * @return The value of formation.
     */
    public String getFormation() {
        return formation;
    }

    /**
     * Set the value formation field.
     *
     * @param formation The value to set.
     */
    public void setFormation(String formation) {
        this.formation = formation;
    }

    /**
     * Get the value of technicalSkills.
     *
     * @return The value of technicalSkills.
     */
    public String getTechnicalSkills() {
        return technicalSkills;
    }

    /**
     * Set the value technicalSkills field.
     *
     * @param technicalSkills The value to set.
     */
    public void setTechnicalSkills(String technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    /**
     * Get the value of study.
     *
     * @return The value of study.
     */
    public String getStudy() {
        return study;
    }

    /**
     * Set the value study field.
     *
     * @param study The value to set.
     */
    public void setStudy(String study) {
        this.study = study;
    }

    @Override
    public int compareTo(final Object o) {
        ProfileModel model = (ProfileModel) o;

        int nameCompare = name.compareTo(model.name);
        if (nameCompare != 0) {
            return nameCompare;
        }

        int firstnameCompare = firstname.compareTo(model.firstname);
        if (firstnameCompare != 0) {
            return firstnameCompare;
        }

        int townCompare = town.compareTo(model.town);
        if (townCompare != 0) {
            return townCompare;
        }

        return title.compareTo(model.title);
    }
}
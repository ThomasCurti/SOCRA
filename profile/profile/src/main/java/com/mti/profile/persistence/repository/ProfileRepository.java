package com.mti.profile.persistence.repository;

import com.mti.profile.persistence.model.ProfileModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Profile Repository that discuss directly with the database
 *
 * extends CrudRepository to implements all CRUD basic operation
 * */
public interface ProfileRepository extends CrudRepository<ProfileModel, Integer> {
}

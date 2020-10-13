package com.mti.profile;

import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Class used to configure the server
 * */
@Configuration
@EnableJpaRepositories(basePackageClasses = { ProfileRepository.class, ProfileModel.class })
public class RestProfilesConfiguration {
}

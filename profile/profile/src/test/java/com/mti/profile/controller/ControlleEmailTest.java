package com.mti.profile.controller;

import com.mti.profile.controller.dto.GetProfileByMailDtoRequest;
import com.mti.profile.controller.dto.GetProfileByMailDtoResponse;
import com.mti.profile.controller.view.EmailController;
import com.mti.profile.domain.service.EmailService;
import com.mti.profile.domain.service.ProfileExcelService;
import com.mti.profile.persistence.repository.ProfileRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.subethamail.wiser.Wiser;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ControlleEmailTest {

    @Autowired
    ProfileRepository profileRepository;

    Wiser inMemorySmtpServer;
    JavaMailSenderImpl javaMailSender;

    ProfileExcelService profileExcelService;
    EmailService emailService;
    EmailController emailController;

    @Before
    public void InitController() {
        profileRepository.deleteAll();

        // Init in memory Smtp server
        inMemorySmtpServer = new Wiser();
        inMemorySmtpServer.setHostname("localhost");
        inMemorySmtpServer.setPort(5555);

        // Init JavaMailSender with in memory Stmt server
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("localhost");
        javaMailSender.setPort(5555);
        javaMailSender.setUsername("socraprojectapi@gmail.com");
        javaMailSender.setPassword("socraprojectapi42");

        inMemorySmtpServer.start();

        profileExcelService = new ProfileExcelService(profileRepository);
        emailService = new EmailService(profileExcelService, javaMailSender);
        emailController = new EmailController(emailService);
    }

    @After
    public void Clear() {
        profileRepository.deleteAll();
        inMemorySmtpServer.stop();
    }

    //feature 3.6
    @Test
    public void get_all_profiles_in_mail() {
        GetProfileByMailDtoResponse response = emailController.getProfileByMail(new GetProfileByMailDtoRequest("socraprojectapi@gmail.com"));

        Assert.assertNotNull(response);
        Assert.assertEquals("Mail sent", response.result);
    }
}

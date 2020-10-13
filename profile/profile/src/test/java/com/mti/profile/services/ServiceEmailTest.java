package com.mti.profile.services;

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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceEmailTest {

    @Autowired
    ProfileRepository repositoryProfile;

    ProfileExcelService profileExcelService;

    EmailService emailService;
    JavaMailSenderImpl javaMailSender;
    Wiser inMemorySmtpServer;


    @Before
    public void Init() {
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

        profileExcelService = new ProfileExcelService(repositoryProfile);
        emailService = new EmailService(profileExcelService, javaMailSender);
    }

    @After
    public void Clear() {
        inMemorySmtpServer.stop();
    }

    //Feature 3.6 Send Email
    @Test(expected = IllegalArgumentException.class)
    public void send_profiles_by_email_with_null_email_should_return_error() {
        try {
            emailService.sendProfilesByEmail(null);
        } catch (MessagingException ignored) {
            Assert.fail();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_profiles_by_email_with_invalid_email_should_return_error() {
        try {
            emailService.sendProfilesByEmail("a");
        } catch (MessagingException ignored) {
            Assert.fail();
        }
    }

    @Test
    public void send_profiles_by_email_should_send_an_email() {
        try {
            emailService.sendProfilesByEmail("test@test.com");
            Assert.assertEquals(1, inMemorySmtpServer.getMessages().size());
        } catch (MessagingException ignored) {
            Assert.fail();
        }
    }

    @Test
    public void send_profiles_by_email_should_send_email_with_proper_from_to_subject() {
        try {
            emailService.sendProfilesByEmail("socraprojectapi@gmail.com");
            Assert.assertEquals(1, inMemorySmtpServer.getMessages().size());
            MimeMessage msg = inMemorySmtpServer.getMessages().get(0).getMimeMessage();

            Assert.assertEquals("Export des profils", msg.getSubject());
            Assert.assertEquals("socraprojectapi@gmail.com", msg.getRecipients(Message.RecipientType.TO)[0].toString());
            Assert.assertEquals("socraprojectapi@gmail.com", msg.getFrom()[0].toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void send_profiles_by_email_should_send_email_with_text_and_attachment() {
        try {
            emailService.sendProfilesByEmail("socraprojectapi@gmail.com");
            Assert.assertEquals(1, inMemorySmtpServer.getMessages().size());
            MimeMessage msg = inMemorySmtpServer.getMessages().get(0).getMimeMessage();
            Multipart multipart = (Multipart) msg.getContent();

            int multipartCount = multipart.getCount();
            int filecount = 0;
            for (int i = 0; i < multipartCount ; i++){
                String fileName = multipart.getBodyPart(i).getFileName();
                if (fileName != null){
                    Assert.assertEquals("profiles.xls", fileName);
                    filecount++;
                }
            }
            Assert.assertEquals(1, filecount);
            Assert.assertEquals(2, multipart.getCount());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}

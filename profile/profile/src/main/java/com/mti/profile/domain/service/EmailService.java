package com.mti.profile.domain.service;

import com.mti.profile.persistence.repository.ProfileRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class EmailService {

    private final ProfileExcelService profileExcelService;
    private final JavaMailSender javaMailSender;

    /**
     * Constructor
     * @param profileExcelService service injected
     * @param javaMailSender service injected
     * */
    public EmailService(final ProfileExcelService profileExcelService,
                        final JavaMailSender javaMailSender) {
        this.profileExcelService = profileExcelService;
        this.javaMailSender = javaMailSender;
    }

    /**
     * Sent all profiles to an email
     * @param email email address where to send the mail
     * @throws MessagingException when email is not valid or when the email sender crashes
     * */
    public void sendProfilesByEmail(String email) throws MessagingException {
        if (!EmailValidator.getInstance().isValid(email)){
            throw new IllegalArgumentException("Email should not be null");
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setFrom("socraprojectapi@gmail.com");
        mimeMessageHelper.setSubject("Export des profils");
        mimeMessageHelper.setText("Vous trouverez la liste des profils en piece jointe de ce mail.");

        try {
            // Get excel
            HSSFWorkbook workbook = profileExcelService.getAllProfilesAsExcel();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());

            mimeMessageHelper.addAttachment("profiles.xls", byteArrayResource);

        } catch (IOException e){
            throw new MessagingException(e.getMessage());
        }

        javaMailSender.send(mimeMessage);
    }
}

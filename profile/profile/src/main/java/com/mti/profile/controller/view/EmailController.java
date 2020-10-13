package com.mti.profile.controller.view;

import com.mti.profile.controller.dto.GetProfileByMailDtoRequest;
import com.mti.profile.controller.dto.GetProfileByMailDtoResponse;
import com.mti.profile.domain.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;

    /**
     * Constructor
     * @param emailService repository injected
     * */
    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Sent all profiles to an email
     * @param request request that is received @see GetProfileByMailDtoRequest
     * @return GetProfileByMailDtoResponse response that is sent to the user @see GetProfileByMailDtoResponse
     * */
    @PostMapping("/")
    public GetProfileByMailDtoResponse getProfileByMail(@RequestBody final GetProfileByMailDtoRequest request){
        try {
            emailService.sendProfilesByEmail(request.email);
            return new GetProfileByMailDtoResponse("Mail sent");
        } catch (Exception e){
            return new GetProfileByMailDtoResponse("Error sending mail");
        }
    }
}

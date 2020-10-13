package com.mti.profile.controller.dto;

/**
 * <b>GetProfileByMailDtoRequest</b>
 * Describe elements that should be present when user make request to send profiles by mail
 * */
public class GetProfileByMailDtoRequest {

    public String email;

    public GetProfileByMailDtoRequest() { }
    public GetProfileByMailDtoRequest(String email) {
        this.email = email;
    }
}

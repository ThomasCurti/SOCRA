package com.mti.profile.controller.dto;

/**
 * <b>GetProfileByMailDtoResponse</b>
 * Describe elements that should present in the response when the user asks to get all profiles by mail
 * */
public class GetProfileByMailDtoResponse {

    public String result;

    public GetProfileByMailDtoResponse() { }
    public GetProfileByMailDtoResponse(String result) {
        this.result = result;
    }
}

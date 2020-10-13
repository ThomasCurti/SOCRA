package com.mti.profile.controller.dto;

import java.util.List;

/**
 * <b>GetProfilesByKeywordsDtoRequest</b>
 * Describe elements that should be present when user make request to search profiles
 * */
public class GetProfilesByKeywordsDtoRequest {
    public List<String> keywords;

    public GetProfilesByKeywordsDtoRequest(final List<String> keywords) {
        this.keywords = keywords;
    }
    public GetProfilesByKeywordsDtoRequest(){}
}

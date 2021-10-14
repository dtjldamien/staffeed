package com.staffeed.backend.Payload.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ResponseAnalyticsResponse {
    private Integer choiceNum;
    private String response;
    private double percentage;

    public ResponseAnalyticsResponse(Integer choiceNum, String response, double percentage) {
        this.choiceNum = choiceNum;
        this.response = response;
        this.percentage = percentage;
    }
}

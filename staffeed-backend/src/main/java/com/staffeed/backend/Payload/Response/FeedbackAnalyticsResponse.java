package com.staffeed.backend.Payload.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class FeedbackAnalyticsResponse {
    private int response;
    private double percentage;

    public FeedbackAnalyticsResponse(int response, double percentage) {
        this.response = response;
        this.percentage = percentage;
    }
}

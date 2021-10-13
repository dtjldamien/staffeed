package com.staffeed.backend.Payload.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AnalyticsResponse {
    private int response;
    private double percentage;

    public AnalyticsResponse(int response, double percentage) {
        this.response = response;
        this.percentage = percentage;
    }
}

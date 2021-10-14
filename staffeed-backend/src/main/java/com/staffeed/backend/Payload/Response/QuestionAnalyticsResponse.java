package com.staffeed.backend.Payload.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staffeed.backend.Model.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString

public class QuestionAnalyticsResponse {
    @JsonIgnoreProperties(value = { "responses" })
    private Question question;
    private double average;

    @DBRef(lazy = true)
    private List<ResponseAnalyticsResponse> responseAnalyticsResponseList;

    public QuestionAnalyticsResponse(Question question, double average, List<ResponseAnalyticsResponse> list) {
        this.question = question;
        this.average = average;
        this.responseAnalyticsResponseList = list;
    }
}

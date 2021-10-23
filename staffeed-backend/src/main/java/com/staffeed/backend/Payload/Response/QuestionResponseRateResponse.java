package com.staffeed.backend.Payload.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staffeed.backend.Model.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class QuestionResponseRateResponse {
    @JsonIgnoreProperties(value = { "responses", "createdBy", "createdDate", "options" })
    private Question question;
    private double responseRate;

    public QuestionResponseRateResponse(double responseRate, Question question) {
        this.responseRate = responseRate;
        this.question = question;
    }

}

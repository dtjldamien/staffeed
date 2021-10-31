package com.staffeed.backend.Payload.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@ToString

public class OverallResponseRateResponse {
    private int totalEmployees;

    @DBRef(lazy = true)
    private List<QuestionResponseRateResponse> questions;

    public OverallResponseRateResponse(int totalEmployees, List<QuestionResponseRateResponse> questions) {
        this.totalEmployees = totalEmployees;
        this.questions = questions;
    }
}

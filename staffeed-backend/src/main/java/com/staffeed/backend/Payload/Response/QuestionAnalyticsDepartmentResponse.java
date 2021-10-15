package com.staffeed.backend.Payload.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.staffeed.backend.Model.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@ToString

public class QuestionAnalyticsDepartmentResponse {
    private String department;

    @DBRef(lazy = true)
    private List<QuestionAnalyticsResponse> questions;

    public QuestionAnalyticsDepartmentResponse(String department, List<QuestionAnalyticsResponse> list) {
        this.department = department;
        this.questions = list;
    }
}

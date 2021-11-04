package com.staffeed.backend.Payload.Response;

import com.staffeed.backend.Model.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@ToString
public class QuestionAnalyticsCategoryResponse {
    private Category category;
    private double average;

    @DBRef(lazy = true)
    private List<QuestionAnalyticsResponse> questions;

    public QuestionAnalyticsCategoryResponse(Category category, List<QuestionAnalyticsResponse> questions) {
        this.category = category;
        this.questions = questions;
    }

    public QuestionAnalyticsCategoryResponse(Category category, double average, List<QuestionAnalyticsResponse> questions) {
        this.category = category;
        this.questions = questions;
        this.average = average;
    }
}

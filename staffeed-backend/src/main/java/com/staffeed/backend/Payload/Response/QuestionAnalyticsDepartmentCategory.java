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
public class QuestionAnalyticsDepartmentCategory {
    private Category category;

    @DBRef(lazy = true)
    private List<QuestionAnalyticsResponse> questions;

    public QuestionAnalyticsDepartmentCategory(Category category, List<QuestionAnalyticsResponse> questions) {
        this.category = category;
        this.questions = questions;
    }
}

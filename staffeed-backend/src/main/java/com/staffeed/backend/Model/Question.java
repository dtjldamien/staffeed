package com.staffeed.backend.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString

@Document(collection = "Question")
public class Question {

    @Id
    private String id;
    private String title;
    private String description;
    private String[] options;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Date createdDate;
    private Date startDate;

    @DBRef(lazy = true)
    private List<Response> responses;

    private Category category;

    public Question(String title, String description, String[] options, Date startDate, Category category) {
        this.title = title;
        this.description = description;
        this.options = options;
        this.startDate = startDate;
        this.responses = new ArrayList<>();
        this.category = category;
    }
}

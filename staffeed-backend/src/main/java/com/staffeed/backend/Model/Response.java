package com.staffeed.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
@ToString

@Document(collection = "Response")
public class Response {
    @Id
    private String id;
    private String response;
    private Integer choiceNum;
    @CreatedDate
    private Date submittedOn;

    @JsonIgnoreProperties(value = { "responses", "password", "email" })
    private User user;

    @JsonIgnoreProperties(value = { "responses" })
    private Question question;

    public Response(String response, User user, Question question) {
        this.response = response;
        this.user = user;
        this.question = question;
        this.choiceNum = Arrays.asList(question.getOptions()).indexOf(response) + 1;
    }

}
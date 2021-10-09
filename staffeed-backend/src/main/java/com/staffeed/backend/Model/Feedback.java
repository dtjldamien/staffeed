package com.staffeed.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "Feedback")
public class Feedback {
    @Id
    private String id;
    private int response;

    @JsonIgnoreProperties(value = { "feedbacks", "password", "email" })
    private User user;

    public Feedback(Integer response, User user) {
        this.response = response;
        this.user = user;
    }

}
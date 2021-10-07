package com.staffeed.backend.Model;

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

}
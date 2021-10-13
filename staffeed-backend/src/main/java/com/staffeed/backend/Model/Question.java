package com.staffeed.backend.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "Question")
public class Question {

    @Id
    private String id;
    private String title;
    private int[] options;
}

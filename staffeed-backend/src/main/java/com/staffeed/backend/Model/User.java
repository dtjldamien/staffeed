package com.staffeed.backend.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@ToString

@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String email;

    @DBRef(lazy=true)
    private List<Feedback> feedbacks;

    public User() {
        this.feedbacks = new ArrayList<>();
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.feedbacks = new ArrayList<>();
    }
}

package com.staffeed.backend.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "employee"),
})
@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;

    @DBRef(lazy=true)
    private List<Feedback> feedbacks;

    public User() {
        this.feedbacks = new ArrayList<>();
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.feedbacks = new ArrayList<>();
    }

}

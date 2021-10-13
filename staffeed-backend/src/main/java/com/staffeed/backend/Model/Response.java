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

@Document(collection = "Response")
public class Response {
    @Id
    private String id;
    private int response;

    @JsonIgnoreProperties(value = { "responses", "password", "email" })
    private User user;

    public Response(Integer response, User user) {
        this.response = response;
        this.user = user;
    }

}
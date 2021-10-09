package com.staffeed.backend.Model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@JsonTypeName("employee")
@Document(collection = "User")
public class Employee extends User {
    private String department;

    public Employee(String username, String password, String email, String department) {
        super(username, password, email);
        this.department = department;
    }
}

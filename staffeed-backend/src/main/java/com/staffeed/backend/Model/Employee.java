package com.staffeed.backend.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Employee extends User {
    private String department;

    public Employee(String name, String password, String email, String department) {
        super(name, password, email);
        this.department = department;
    }
}

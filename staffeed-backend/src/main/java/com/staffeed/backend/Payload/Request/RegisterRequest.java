package com.staffeed.backend.Payload.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}

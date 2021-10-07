package com.staffeed.backend.Payload.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtResponse {

    private String token;
    private String id;

    public JwtResponse(String token, String id) {
        this.token = token;
        this.id = id;
    }
}

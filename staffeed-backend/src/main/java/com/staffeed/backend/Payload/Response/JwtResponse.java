package com.staffeed.backend.Payload.Response;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String id;

    public JwtResponse(String token, String id) {
        this.token = token;
        this.id = id;
    }
}

package com.staffeed.backend.Payload.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}

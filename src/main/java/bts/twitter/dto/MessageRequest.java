package bts.twitter.dto;

import lombok.Data;

@Data
public class MessageRequest {

    private String message;
    private long userId;
}

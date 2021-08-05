package bts.twitter.dto;

import bts.twitter.model.Message;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageDto {
    private long id;

    private String message;

    private LocalDateTime creationDate;

    private String image;

    private String author;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .message(message.getMessage())
                .creationDate(message.getCreationDate())
                .image(message.getImage())
                .author(message.getAuthor().getName())
                .build();
    }
}

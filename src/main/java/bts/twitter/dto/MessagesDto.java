package bts.twitter.dto;

import bts.twitter.model.WeatherData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessagesDto {

    private List<MessageDto> messages;
    private WeatherData weather;
}

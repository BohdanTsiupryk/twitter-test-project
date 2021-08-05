package bts.twitter.controller;

import bts.twitter.dto.MessageDto;
import bts.twitter.dto.MessageRequest;
import bts.twitter.dto.MessagesDto;
import bts.twitter.model.Message;
import bts.twitter.model.User;
import bts.twitter.model.WeatherData;
import bts.twitter.repository.MessageRepo;
import bts.twitter.repository.UserRepo;
import bts.twitter.service.AwsS3Service;
import bts.twitter.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final WeatherService weatherService;
    private final AwsS3Service awsS3Service;

    public MessageRestController(MessageRepo messageRepo, UserRepo userRepo, WeatherService weatherService, AwsS3Service awsS3Service) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        this.weatherService = weatherService;
        this.awsS3Service = awsS3Service;
    }

    @GetMapping
    public ResponseEntity<MessagesDto> messages() {
        List<Message> messages = messageRepo.findAll();
        WeatherData lviv = weatherService.getWeather("lviv");

        List<MessageDto> dtos = messages.stream()
                .map(MessageDto::from)
                .collect(Collectors.toList());

        MessagesDto messagesDto = MessagesDto.builder()
                .messages(dtos)
                .weather(lviv)
                .build();

        return ResponseEntity.status(500).body(messagesDto);
    }

    @PostMapping
    public ResponseEntity<URI> addMessage(@RequestBody MessageRequest request) {
        Message message = new Message(
                request.getMessage(),
                LocalDateTime.now(),
                userRepo.getById(request.getUserId()),
                "awsS3Service.saveFile(multipartFile)"
        );

        Message save = messageRepo.save(message);

        return ResponseEntity.created(URI.create("/api/messages/" + save.getId())).build();
    }
}

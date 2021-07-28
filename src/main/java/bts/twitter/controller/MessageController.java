package bts.twitter.controller;

import bts.twitter.model.Message;
import bts.twitter.model.User;
import bts.twitter.model.Weather;
import bts.twitter.model.WeatherData;
import bts.twitter.repository.MessageRepo;
import bts.twitter.repository.UserRepo;
import bts.twitter.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final WeatherService weatherService;

    @Value("${image.dir}")
    private String directoryPath;

    public MessageController(MessageRepo messageRepo, UserRepo userRepo, WeatherService weatherService) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        this.weatherService = weatherService;
    }

    @GetMapping
    public String messages(Model model) {
        List<Message> messages = messageRepo.findAll();
        List<User> users = userRepo.findAll();
        WeatherData lviv = weatherService.getWeather("lviv");

        Weather weather = lviv.getWeather();

        model.addAttribute("messages", messages);
        model.addAttribute("users", users);
        model.addAttribute("cityname", lviv.getCityName());
        model.addAttribute("description", weather.getDescription());

        return "messages";
    }

    @PostMapping
    public String addMessage(@RequestParam("message") String newMessage,
                             @RequestParam("userId") long userId,
                             @RequestParam("image") MultipartFile multipartFile
                             ) {
        Message message = new Message(
                newMessage,
                LocalDateTime.now(),
                userRepo.getById(userId),
                saveFile(multipartFile)
        );

        messageRepo.save(message);

        return "redirect:/messages";
    }

    @GetMapping("/delete/{id}")
    public String deleteMessage(@PathVariable("id") long id) {
        messageRepo.deleteById(id);

        return "redirect:/messages";
    }

    private String saveFile(MultipartFile multipartFile) {
        File dir = Paths.get(directoryPath).toFile();

        if (!dir.exists()) {
            dir.mkdir();
        }

        String randomName = UUID.randomUUID().toString();
        String result = randomName + "." + multipartFile.getOriginalFilename();
//        File newFile = Paths.get(directoryPath + "/" + result).toFile();
//
//        try {
//            multipartFile.transferTo(newFile);
//        } catch (IOException e) {
//            System.out.println(e);
//        }

        return result;
    }
}

package bts.twitter.controller;

import bts.twitter.model.Message;
import bts.twitter.model.User;
import bts.twitter.repository.MessageRepo;
import bts.twitter.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    public MessageController(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String messages(Model model) {
        List<Message> messages = messageRepo.findAll();
        List<User> users = userRepo.findAll();

        model.addAttribute("messages", messages);
        model.addAttribute("users", users);

        return "messages";
    }

    @PostMapping
    public String addMessage(@RequestParam("message") String newMessage,
                             @RequestParam("userId") long userId) {
        Message message = new Message(
                newMessage,
                LocalDateTime.now(),
                userRepo.getById(userId)
        );

        messageRepo.save(message);

        return "redirect:/messages";
    }

    @GetMapping("/delete/{id}")
    public String deleteMessage(@PathVariable("id") long id) {
        messageRepo.deleteById(id);

        return "redirect:/messages";
    }
}

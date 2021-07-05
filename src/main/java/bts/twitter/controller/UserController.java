package bts.twitter.controller;

import bts.twitter.model.User;
import bts.twitter.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable("id") long userId,
                          Model model) {
        User user = userRepo.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        model.addAttribute("user", user);
        model.addAttribute("messages", user.getMessages());

        return "user-info";
    }
}

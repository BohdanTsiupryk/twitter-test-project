package bts.twitter.controller;

import bts.twitter.model.Role;
import bts.twitter.model.User;
import bts.twitter.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String getView() {
        return "registration";
    }

    @PostMapping
    public String registration(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("password_confirm") String passwordConfirm,
            Model model
            ) {

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error_message", "passwords not equals");

            return "registration";
        }
        User user = new User(
                name,
                passwordEncoder.encode(password),
                Role.USER
        );

        userRepo.save(user);

        return "redirect:/messages";
    }
}

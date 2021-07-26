package bts.twitter.controller;

import bts.twitter.MailService;
import bts.twitter.model.Role;
import bts.twitter.model.User;
import bts.twitter.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailService mailService;

    @GetMapping
    public String getView() {
        return "registration";
    }

    @PostMapping
    public String registration(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("password_confirm") String passwordConfirm,
            Model model
            ) {

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error_message", "passwords not equals");

            return "registration";
        }

        String trim = name.trim();

        String confirmationCode = generateRandomCode();

        User user = new User(
                trim.isEmpty() ? "username" : trim,
                email,
                passwordEncoder.encode(password),
                confirmationCode,
                Role.USER
        );

        userRepo.save(user);

        mailService.sendConfirmCode(email, confirmationCode);

        return "redirect:/messages";
    }

    @GetMapping("/confirm/{code}")
    public String confirm(@PathVariable String code, Model model) {

        Optional<User> user = userRepo.findByConfirmationCode(code);

        if (user.isPresent()) {
            User fromDb = user.get();

            fromDb.setConfirmationCode("");
            userRepo.save(fromDb);

            model.addAttribute("message", "You activate your account");
        } else {
            model.addAttribute("message", "We don`t found your confirmation code");
        }

        return "login";
    }

    private String generateRandomCode() {
        return UUID.randomUUID().toString();
    }
}

package bts.twitter.controller;

import bts.twitter.model.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(@AuthenticationPrincipal User user, Model model) {

        if (user != null) {

            model.addAttribute("loggined", user);
        }
        return "login";
    }
}

package bts.twitter.controller;

import bts.twitter.model.Role;
import bts.twitter.model.User;
import bts.twitter.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepo userRepo;

    public AdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    private String getAdminPage(Model model) {
        List<User> users = userRepo.findAll();

        model.addAttribute("users", users);

        return "admin-page";
    }

    @GetMapping("/add-admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    private String makeAdmin(@PathVariable String id, Model model) {
        long userId = Long.parseLong(id);

        User user = userRepo.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        user.setRole(Role.ADMIN);

        userRepo.save(user);

        return "redirect:/admin";
    }
}

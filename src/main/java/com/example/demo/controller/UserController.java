package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{id}")
    public String userEditForm(@PathVariable (value = "id") long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/main";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("users", res);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

//    @GetMapping("{user}")
//    public String userEditForm(@PathVariable User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("roles", Role.values());
//        return "userEdit";
//    }

}

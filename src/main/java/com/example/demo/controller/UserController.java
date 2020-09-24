package com.example.demo.controller;

import com.example.demo.domain.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @PostMapping("{id}")
    public String userSave(
//            @PathVariable (value = "id") long id,
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam String userId
//            @RequestParam("userId") User user,
            ) {
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }

        userRepository.save(user);

        return "redirect:/user";
    }

}

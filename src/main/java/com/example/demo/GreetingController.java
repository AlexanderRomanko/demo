package com.example.demo;

import com.example.demo.entity.MessageEntity;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("main")
    public String main(Map<String, Object> model) {
    Iterable<MessageEntity> messages = messageRepo.findAll();
    model.put("messages", messages);
        return "main";
    }

    @PostMapping("main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        MessageEntity message = new MessageEntity(text, tag);
        messageRepo.save(message);
        Iterable<MessageEntity> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<MessageEntity> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }

}
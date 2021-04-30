package com.squirrel.restful.controller;

import com.squirrel.restful.model.Message;
import com.squirrel.restful.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // Get all
    @GetMapping("/messages")
    public List<Message> list() {
        return this.messageRepository.findAll();
    }

    // New one
    @PostMapping("/message")
    public Message create(Message message) {
        message = this.messageRepository.save(message);
        return message;
    }

    // Use put
    @PutMapping("/message")
    public Message modify(Message message) {
        return this.messageRepository.update(message);
    }

    // Update text
    @PatchMapping("/message/text")
    public Message patch(Message message) {
        return this.messageRepository.updateText(message);
    }

    // Get by id
    @GetMapping("/message/{id}")
    public Message get(@PathVariable Long id) {
        return this.messageRepository.findMessage(id);
    }

    // Delete by id
    @DeleteMapping("/message/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }
}

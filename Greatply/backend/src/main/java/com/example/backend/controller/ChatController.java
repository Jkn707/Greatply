package com.example.backend.controller;

import com.example.backend.model.ChatRequest;
import com.example.backend.model.ChatResponse;
import com.example.backend.service.GPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final GPTService chatService;

    //Instanciating Chat Service
    @Autowired
    public ChatController(GPTService chatService) {
        this.chatService = chatService;
    }
    //Sends a request and expects a response
    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String response = chatService.generateResponse(request.getMessage());
        return new ChatResponse(response);
    }
}
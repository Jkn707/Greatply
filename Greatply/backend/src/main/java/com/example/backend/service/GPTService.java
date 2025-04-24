package com.example.backend.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class GPTService {

    private final ChatModel chatModel;

    @Autowired
    public GPTService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateResponse(String userMessage) {

        List<Message> messages = new ArrayList<>();
        
        messages.add(new SystemMessage("Give me the most diplomatic way to respond to this message:"));
        
        messages.add(new UserMessage(userMessage));
        
        Prompt prompt = new Prompt(messages);
        
        ChatResponse response = chatModel.call(prompt);
        
        return response.getResult().getOutput().getText();
    }
}
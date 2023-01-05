package com.bachar.springbootlibrary.controller;

import com.bachar.springbootlibrary.entity.Message;
import com.bachar.springbootlibrary.requrestmodels.AdminQuestionRequest;
import com.bachar.springbootlibrary.service.MessagesService;
import com.bachar.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }


    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token, @RequestBody Message messageRequest) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value = "Authorization") String token, @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if(userEmail == null || !userEmail.equals("admin@email.com")) {
            throw new Exception("Administration page only.");
        }

        messagesService.putMessage(adminQuestionRequest, userEmail);

    }
}

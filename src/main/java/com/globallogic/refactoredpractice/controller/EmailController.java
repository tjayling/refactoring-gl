package com.globallogic.refactoredpractice.controller;

import com.globallogic.refactoredpractice.model.Email;
import com.globallogic.refactoredpractice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity sendMail(@NonNull @RequestBody Email email) {
        return emailService.sendMail(email);
    }
}

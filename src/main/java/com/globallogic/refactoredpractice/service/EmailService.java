package com.globallogic.refactoredpractice.service;

import com.globallogic.refactoredpractice.model.Email;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity sendMail(Email email);
}

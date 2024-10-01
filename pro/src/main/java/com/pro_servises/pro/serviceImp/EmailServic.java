package com.pro_servises.pro.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServic {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRatingPrompt(String customerEmail, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customerEmail);
        message.setSubject("Rate your recent purchase");
        message.setText("Your order for " + productName + " is marked as done. Please rate and comment.");
        mailSender.send(message);
    }
}

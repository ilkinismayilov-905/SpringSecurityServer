package com.example.repository.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmailService {

    private final OtpServiceImpl otpServiceImpl;

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender,
                         OtpServiceImpl otpServiceImpl1) {
        this.javaMailSender = javaMailSender;
        this.otpServiceImpl = otpServiceImpl1;
    }

    public void sendOtpEmail(String toEmail){
        String otpCode=GenerateCodeUtil.generateCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Sənin OTP Kodun");
        message.setText("OTP Kodun: " + otpCode);
        message.setFrom("sənin-email@gmail.com");

        javaMailSender.send(message);
        otpServiceImpl.saveOtp("Vuqar",toEmail,otpCode);
    }
}

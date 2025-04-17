package com.example.repository.impl;

import com.example.entity.OTP;
import com.example.repository.OtpRepository;
import jakarta.transaction.Transactional;

import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class OtpServiceImpl {

    private OtpRepository otpRepository;

    @Autowired
    public OtpServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public void saveOtp(String username,String email,String OtpCode){
        OTP otp = new OTP();
        otp.setEmail(email);
        otp.setCode(GenerateCodeUtil.generateCode());
        otp.setUsername(username);
        otp.setCreatedAt(LocalDateTime.now());
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));// OTP 5 dəqiqəlik etibarlı

        otpRepository.save(otp);
    }
}

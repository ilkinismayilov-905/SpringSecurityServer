package com.example.repository.impl;

import com.example.entity.OTP;
import com.example.entity.UserAuth;
import com.example.repository.OtpRepository;
import com.example.repository.UserAuthRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserAuthServiceImpl {
    private PasswordEncoder passwordEncoder;
    private UserAuthRepository userAuthRepository;
    private OtpRepository otpRepository;

    @Autowired
    public UserAuthServiceImpl(PasswordEncoder passwordEncoder,
                               UserAuthRepository userAuthRepository,
                               OtpRepository otpRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userAuthRepository = userAuthRepository;
        this.otpRepository = otpRepository;
    }

    public void save(UserAuth user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userAuthRepository.save(user);
    }

    public void auth(UserAuth user){
        Optional<UserAuth> o = userAuthRepository.findUserByUsername(user.getUsername());

        if(o.isPresent()){
            UserAuth u  = o.get();
            if(passwordEncoder.matches(user.getPassword(), u.getPassword())){
                renewOtp(u);
            }else {
                throw new BadCredentialsException
                        ("OTP Error.");
            }
        }else {
            throw new BadCredentialsException
                    ("Passwords should be matched.");
        }
    }

    private void renewOtp(UserAuth u){
        String code = GenerateCodeUtil
                .generateCode();

        Optional<OTP> userOtp = otpRepository.findOtpByUsername(u.getUsername());

        if(userOtp.isPresent()){
            OTP otp = userOtp.get();
            otp.setCode(code);
        }else {
            OTP otp = new OTP();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }


    public boolean check(OTP otpToValidate){
        Optional<OTP> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());

        if (userOtp.isPresent()){
            OTP otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }
        return false;
    }
}

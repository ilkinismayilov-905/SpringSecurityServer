package com.example.configFilters;

import com.example.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthenticationServerProxy {


    private RestTemplate rest;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public AuthenticationServerProxy(RestTemplate rest) {
        this.rest = rest;
    }

    public void sendAuth(String username, String password){
        String url = baseUrl + "/auth/user/auth";

        var body = new User();
        body.setUsername(username);
        body.setPassword(password);

        var request = new HttpEntity<>(body);

        rest.postForEntity(url,request, Void.class);
    }

    public boolean sendOTP(String username,
                           String code){
        String url = baseUrl + "/auth/otp/check";

        var body = new User();
        body.setUsername(username);
        body.setCode(code);

        var request = new HttpEntity<>(body);

        var response = rest.postForEntity(url,request, Void.class);

        return response
                .getStatusCode()
                .equals(HttpStatus.OK);
    }
}

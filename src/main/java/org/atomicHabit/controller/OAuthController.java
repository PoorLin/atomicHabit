package org.atomicHabit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OAuthController {

    @GetMapping("/getGooglePublicKey")
    public ResponseEntity<String> getGooglePublicKey() {
        RestTemplate restTemplate = new RestTemplate();
        String jwkSetUri = "https://www.googleapis.com/oauth2/v3/certs";
        ResponseEntity<String> response = restTemplate.getForEntity(jwkSetUri, String.class);
        return response;
    }
}

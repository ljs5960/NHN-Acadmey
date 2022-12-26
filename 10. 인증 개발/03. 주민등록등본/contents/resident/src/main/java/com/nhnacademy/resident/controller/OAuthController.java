//package com.nhnacademy.resident.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.HashMap;
//import java.util.Map;
//import net.minidev.json.JSONObject;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.HttpHeaders;
//
//@Controller
//public class OAuthController {
//    @GetMapping("/login/github")
//    public void requestRestApi() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        JSONObject parameter = new JSONObject();
//        parameter.put("client_id", "7655f8dc61308e4bd3e5");
//
//        HttpEntity<String> req = new HttpEntity<>(parameter.toJSONString(), headers);
//        RestTemplate template = new RestTemplate();
//        ResponseEntity<String> responseEntity = template.postForEntity("https://github.com/login/oauth/authorize", req, String.class);
//    }
//}

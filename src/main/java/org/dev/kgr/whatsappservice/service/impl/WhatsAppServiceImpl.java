package org.dev.kgr.whatsappservice.service.impl;

import org.dev.kgr.whatsappservice.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppServiceImpl implements WhatsAppService {

    @Value("${whatsapp.api-url}")
    private String apiUrl;

    @Value("${whatsapp.phone-number-id}")
    private String phoneNumberId;

    @Value("${whatsapp.access-token}")
    private String accessToken;

    private final RestTemplate restTemplate;

    public WhatsAppServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendTextMessage(String to, String message) {
        String url = apiUrl+ "/" + phoneNumberId + "/messages";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String payload = """
        {
            "message_product" : "whatsapp",
            "to" : "%s",
            "type" : "text",
            "text" : { "body" : "%s" }
        }
        """.formatted(to, message);

        HttpEntity<String> request = new HttpEntity<>(payload, headers);
        restTemplate.postForEntity(url, request, String.class);
    }
}
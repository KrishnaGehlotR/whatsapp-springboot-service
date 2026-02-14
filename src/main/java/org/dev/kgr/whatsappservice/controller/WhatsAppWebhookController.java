package org.dev.kgr.whatsappservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whatsapp")
public class WhatsAppWebhookController {

    @Value("${whatsapp.verify-token}")
    private String verifyToken;

    @GetMapping("/webhook")
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge){

        if("subscribe".equals(mode) && verifyToken.equals(token)){
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification failed");
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> receiveMessage(@RequestBody String payload){
        System.out.println("Webhook payload: " + payload);
        return ResponseEntity.ok().build();
    }
}

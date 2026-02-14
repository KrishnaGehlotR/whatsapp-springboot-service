package org.dev.kgr.whatsappservice.controller;

import org.dev.kgr.whatsappservice.service.WhatsAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whatsapp")
public class WhatsAppSendMessageController {

    private final WhatsAppService whatsAppService;

    public WhatsAppSendMessageController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(
            @RequestParam String to,
            @RequestParam String message) {
        whatsAppService.sendTextMessage(to, message);
        return ResponseEntity.ok("Message sent successfully");
    }
}

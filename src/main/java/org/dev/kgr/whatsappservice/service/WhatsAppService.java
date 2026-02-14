package org.dev.kgr.whatsappservice.service;

import org.springframework.stereotype.Service;

public interface WhatsAppService {

    void sendTextMessage(String to, String message);
}

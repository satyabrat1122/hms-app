package com.hms.services;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    public String sendWhatsAppMessage(String to,String from, String messageContent) {
        try {
            Message message = Message.creator(
                            new PhoneNumber(to),
                            new PhoneNumber(from),                   // Twilio WhatsApp number
                            messageContent)                             // the message
                    .create();

            return "Message sent successfully! SID: " + message.getSid();
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }

}

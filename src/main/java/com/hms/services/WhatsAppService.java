package com.hms.services;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    public String sendWhatsAppMessage(String to,String from, String messageContent) {
        try {
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("whatsapp:"+to),
                            new com.twilio.type.PhoneNumber("whatsapp:"+from),
                            messageContent
                            )
                    .create();

            return "Message sent successfully! SID: " + message.getSid();
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }

}

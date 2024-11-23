package com.hms.services;


import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    public String sendSms(String to,String from,String body){
        Message message=Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(from),
                body
        ).create();
        return message.getSid();
    }
}
package com.hms.configs;

import com.twilio.Twilio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {


    @Bean
    public void init(){
        Twilio.init("ACc85426c94e882a3b9bf13b95193dc3a0","1feb29b2f34dc962db357d20a4374ae8");
    }
}

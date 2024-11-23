package com.hms.services;


import com.hms.util.OTPUtil;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
public class OTPService {

    @Autowired
    private OTPUtil otpUtil;
    @Autowired
    private TwilioService twilioService;

    private final Map<String, OTPData> otpStorage = new HashMap<>();
    private static final long OTP_EXPIRY_TIME = 5 * 60 * 1000; // OTP expiry time: 5 minutes


    public String generateOTP(String phoneNumber) {
        String otp = otpUtil.generateOTP();
        otpStorage.put(phoneNumber, new OTPData(otp, System.currentTimeMillis() + OTP_EXPIRY_TIME));

        twilioService.sendSms(phoneNumber, "Your OTP number is :" + otp);


        return otp;
    }

   public boolean validateOTP(String mobileNumber,String otp){
        OTPData storedOTPData=otpStorage.get(mobileNumber);
        if (storedOTPData==null){
            return false;

        }
        if(System.currentTimeMillis()>storedOTPData.getExpiryTime()){
            otpStorage.remove(mobileNumber);
            return false;

        }
        if (storedOTPData.getOTP().equals(otp)){
            otpStorage.remove(mobileNumber);
            return true;
        }
        return false;
   }


}

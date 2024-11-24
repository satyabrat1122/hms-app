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
    @Autowired
    private WhatsAppService whatsAppService;

    private final Map<String, SMSOTPData> otpStorage = new HashMap<>();
    private final Map<String, WhatsappOTPData> otpStorage2 = new HashMap<>();
    private static final long OTP_EXPIRY_TIME = 5 * 60 * 1000; // OTP expiry time: 5 minutes



    //For SMS OTP
    public String generateOTP(String phoneNumber) {
        String otp = otpUtil.generateOTP();
        otpStorage.put(phoneNumber, new SMSOTPData(otp, System.currentTimeMillis() + OTP_EXPIRY_TIME));
        twilioService.sendSms(phoneNumber, "Your OTP number is :" + otp);
        return otp;
    }



     //For Whatsapp OTP
    public String generateWhatsappOTP(String phoneNumber) {
        String otp = otpUtil.generateOTP();
        otpStorage2.put(phoneNumber, new WhatsappOTPData(otp, System.currentTimeMillis() + OTP_EXPIRY_TIME));
        whatsAppService.sendWhatsAppMessage(phoneNumber, "Your OTP number is :" + otp);
        return otp;
    }



    //Validating OTP
   public boolean validateOTP(String mobileNumber,String otp){
        SMSOTPData storedOTPData=otpStorage.get(mobileNumber);
        WhatsappOTPData storedOTPData2=otpStorage2.get(mobileNumber);
        if (storedOTPData==null){
            return false;
        }
        if(System.currentTimeMillis()>storedOTPData.getExpiryTime()){
            otpStorage.remove(mobileNumber);
            return false;
        }
        if (storedOTPData.getOTP().equals(otp)||storedOTPData2.getWhatsappOtp().equals(otp)){
            otpStorage.remove(mobileNumber);
            return true;
        }
        return false;
   }

}

package com.hms.service;

public class WhatsappOTPData {
    private final String whatsappOtp;
    private final long expiryTime;
    public WhatsappOTPData(String whatsappOtp, long expiryTime) {
        this.whatsappOtp=whatsappOtp;
        this.expiryTime = expiryTime;
    }

    public String getWhatsappOtp() {
        return whatsappOtp;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}

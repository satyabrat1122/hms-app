package com.hms.services;

 class OTPData{
    private final String otp;
    private final long expiryTime;
    public OTPData(String otp,long expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }
    public String getOTP() {
        return otp;
    }
    public long getExpiryTime() {
        return expiryTime;
    }
}


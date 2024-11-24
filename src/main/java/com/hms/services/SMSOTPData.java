package com.hms.services;

 class SMSOTPData {
    private final String smsOtp;
    private final long expiryTime;
    public SMSOTPData(String smsOtp, long expiryTime) {
        this.smsOtp = smsOtp;
        this.expiryTime = expiryTime;
    }
    public String getOTP() {
        return smsOtp;
    }
    public long getExpiryTime() {
        return expiryTime;
    }
}


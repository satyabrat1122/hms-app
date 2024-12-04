//package com.hms.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
////@Entity
////@Table(name = "otp_db")
//public class OTPDb {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "phone_number", nullable = false)
//    private String phoneNumber;
//
//    @Column(name = "otp", nullable = false)
//    private String otp;
//
//    @Column(name = "expiry_time", nullable = false)
//    private Long expiryTime;
//
//}
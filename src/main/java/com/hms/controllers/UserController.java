package com.hms.controllers;

import com.hms.entity.AppUser;
import com.hms.payloads.LoginDto;
import com.hms.payloads.TokenDto;
import com.hms.payloads.UserDto;

import com.hms.repository.AppUserRepository;
import com.hms.repository.BookingsRepository;
import com.hms.services.JWTService;
import com.hms.services.OTPService;
import com.hms.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    private JWTService jwtService;
    private OTPService otpService;
    private final BookingsRepository bookingsRepository;
    private final AppUserRepository appUserRepository;

    public UserController(UserService userService, JWTService jwtService, OTPService otpService,
                          BookingsRepository bookingsRepository,
                          AppUserRepository appUserRepository) {
        this.userService = userService;

        this.jwtService = jwtService;
        this.otpService = otpService;
        this.bookingsRepository = bookingsRepository;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/signup")
    //localhost:8080/api/v1/users/signup
    public ResponseEntity<?> signUp(
            @RequestBody UserDto userDto
            )
    {
        String s = userService.alreadyExist(userDto);
        if(s!=null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        UserDto dto=userService.createUser(userDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestBody LoginDto loginDto
            ){
        String status=userService.login(loginDto);
        return new ResponseEntity<>(status,HttpStatus.OK);

    }
    @PostMapping("/signup-property-owner")
    public ResponseEntity<?> createPropertyOwnerUser(
            @RequestBody UserDto userDto
    )
    {
        String s = userService.alreadyExist(userDto);
        if(s!=null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        UserDto dto=userService.createPropertyOwner(userDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);

    }

//    @PostMapping("/generate-login-otp")
//    public String login(@RequestParam String mobileNumber){
//         otpService.generateOTP(mobileNumber);
//        return "OTP Generated";
//    }

    @PostMapping("/validate-login-otp")
    public ResponseEntity<?> validateOTP(@RequestParam String mobileNumber , @RequestParam String otp) {

        boolean isValid = otpService.validateOTP(mobileNumber, otp);
        if (isValid) {
            Optional<AppUser> byMobileNumber = appUserRepository.findByMobileNumber(mobileNumber);
            AppUser appUser = byMobileNumber.get();
            String token = jwtService.generateToken(appUser.getUsername());
            TokenDto tokenDto=new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setStatus("Created");
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or Expired OTP",HttpStatus.OK);
        }
    }

}

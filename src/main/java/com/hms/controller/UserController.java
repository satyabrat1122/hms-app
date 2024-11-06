package com.hms.controller;

import com.hms.payload.LoginDto;
import com.hms.payload.TokenDto;
import com.hms.payload.UserDto;

import com.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
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
        String token=userService.login(loginDto);
        TokenDto tokenDto=new TokenDto();
        tokenDto.setToken(token);
        tokenDto.setStatus("JWT");
        return new ResponseEntity<>(tokenDto,HttpStatus.OK);

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

}

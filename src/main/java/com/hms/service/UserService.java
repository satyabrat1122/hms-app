package com.hms.service;

import com.hms.config.SecurityConfig;
import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.payload.UserDto;
import com.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, SecurityConfig securityConfig, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;

    }


    public String alreadyExist(UserDto userDto) {
        AppUser appUser=new AppUser();
        Optional<AppUser> byUsername = appUserRepository.findByUsername(userDto.getUsername());
        if(byUsername.isPresent()){
            return "Already Username Exist";
        }
        Optional<AppUser> byEmail = appUserRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent()){
            return "Already Email Exist";
        }
        return null;
    }

    AppUser mapToEntity(UserDto userDto){
        AppUser appUser=new AppUser();
        appUser.setUsername(userDto.getUsername());
        appUser.setEmail(userDto.getEmail());
        appUser.setName(userDto.getName());
        appUser.setPassword(userDto.getPassword());
        return appUser;
    }

    UserDto mapToDto(AppUser appUser){
        UserDto dto=new UserDto();
        dto.setUsername(appUser.getUsername());
        dto.setEmail(appUser.getEmail());
        dto.setName(appUser.getName());

        return dto;
    }

    public UserDto createUser(UserDto userDto) {
        AppUser appUser = mapToEntity(userDto);
        String hashpw = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(5));
        appUser.setPassword(hashpw);
        AppUser save = appUserRepository.save(appUser);
        UserDto userDto1 = mapToDto(save);
        return userDto1;
    }



    public String login(LoginDto loginDto) {
        AppUser user=new AppUser();
        Optional<AppUser> username = appUserRepository.findByUsername(loginDto.getUsername());
        AppUser appUser = username.get();
        boolean checkpw = BCrypt.checkpw( loginDto.getPassword(),appUser.getPassword());
        if(checkpw){
            return jwtService.generateToken(loginDto.getUsername());
        }
        else{
            return "Invalid Username Or Password";
        }
    }
}

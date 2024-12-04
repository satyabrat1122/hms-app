package com.hms.services;

import com.hms.entity.AppUser;
import com.hms.repository.AppUserRepository;
import com.hms.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private MockMvc mockMvc;
    @Mock
     AppUserRepository appUserRepository;
    @InjectMocks
    UserService userService;

    AppUser RECORD_1=new AppUser(1L,"john","john@gmail.com","john","testing","sdmin","84749802049");
    AppUser RECORD_2=new AppUser(2L,"mohn","mohn@gmail.com","mohn","testing","sdmin","84749202049");

    @Test
    void createUserShouldCreateUserSuccessfully() {
        List<AppUser> appUser=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2));
        when(appUserRepository.findAll()).thenReturn(appUser);

         List<AppUser> allUsers = userService.getAllUsers();
        assertEquals(2, allUsers.size());
        assertEquals(allUsers.get(1).getName(),appUser.get(1).getName());
   }
}
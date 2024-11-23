package com.hms.controllers;

import com.hms.implementation.StateServiceImpl;
import com.hms.payloads.StateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/state")
public class StateController {

    private StateServiceImpl stateServiceImpl;

    public StateController(StateServiceImpl stateServiceImpl) {
        this.stateServiceImpl = stateServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addState(
            @RequestBody StateDto stateDto
    ){
        String s=stateServiceImpl.addState(stateDto);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}

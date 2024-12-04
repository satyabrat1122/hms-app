package com.hms.controllers;

import com.hms.entity.Rooms;
import com.hms.service.RoomsServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {
    private RoomsServiceImpl roomsServiceImpl;

    public RoomsController(RoomsServiceImpl roomsServiceImpl) {
        this.roomsServiceImpl = roomsServiceImpl;
    }


    @PostMapping("/{id}")
    //
    public String addRoomsDetails(
            @PathVariable("id") long propertyId,
            @RequestBody Rooms rooms
    ){
        String s = roomsServiceImpl.addRooms(propertyId,rooms);
        return s;
    }
}

package com.hms.implementation;

import com.hms.entity.Property;
import com.hms.entity.Rooms;
import com.hms.repository.PropertyRepository;
import com.hms.repository.RoomsRepository;
import com.hms.services.RoomsService;
import org.springframework.stereotype.Service;

@Service
public class RoomsServiceImpl implements RoomsService {
    private PropertyRepository propertyRepository;
    private RoomsRepository roomsRepository;

    public RoomsServiceImpl(PropertyRepository propertyRepository, RoomsRepository roomsRepository) {
        this.propertyRepository = propertyRepository;
        this.roomsRepository = roomsRepository;
    }

    @Override
    public String addRooms(long propertyId, Rooms rooms) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(()->new RuntimeException("Property not found"));
        rooms.setProperty(property);
        roomsRepository.save(rooms);
        return "Room created";
    }
}

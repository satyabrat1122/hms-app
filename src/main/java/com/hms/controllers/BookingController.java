package com.hms.controllers;

import com.hms.entity.Bookings;
import com.hms.service.BookingService;

import com.hms.payloads.BookingDto;
import com.hms.repository.BookingsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private BookingsRepository bookingsRepository;
    private BookingService bookingService;


    public BookingController( BookingsRepository bookingsRepository, BookingService bookingService) {
        this.bookingsRepository = bookingsRepository;
        this.bookingService = bookingService;

    }

    //Create Booking
    @PostMapping("/create-booking")
    public String createBooking(
            @RequestParam long propertyId,
            @RequestBody BookingDto bookingsDto,
            @RequestParam String type
    ) {
        String booking = bookingService.createBooking(propertyId, bookingsDto, type);
        return booking;
    }



    //Get bookings details
    @GetMapping("/getBookings")
    //localhost:8080/api/v1/bookings/getBookings
    public ResponseEntity<?> getBookings(@RequestParam String name
    ){
        List<Bookings> bookingsByEmail = bookingsRepository.findByName(name);
        return new ResponseEntity<>(bookingsByEmail,HttpStatus.OK);
    }
}
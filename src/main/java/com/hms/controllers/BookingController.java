package com.hms.controllers;

import com.hms.entity.Bookings;
import com.hms.implementation.BookingServiceImpl;
import com.hms.payloads.BookingDto;
import com.hms.repository.BookingsRepository;
import com.hms.repository.PropertyRepository;
import com.hms.repository.RoomsRepository;
import com.hms.services.PdfService;
import com.hms.services.TwilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private PdfService pdfService;
    private BookingsRepository bookingsRepository;
    private PropertyRepository propertyRepository;
    private RoomsRepository roomsRepository;
    private BookingServiceImpl bookingServiceImpl;
    private TwilioService twilioService;

    public BookingController(PdfService pdfService, BookingsRepository bookingsRepository, PropertyRepository propertyRepository, RoomsRepository roomsRepository, BookingServiceImpl bookingServiceImpl, TwilioService twilioService) {
        this.pdfService = pdfService;
        this.bookingsRepository = bookingsRepository;
        this.propertyRepository = propertyRepository;
        this.roomsRepository = roomsRepository;
        this.bookingServiceImpl = bookingServiceImpl;
        this.twilioService = twilioService;
    }


    @PostMapping("/create-booking")
    public String createBooking(
            @RequestParam long propertyId,
            @RequestBody BookingDto bookingsDto,
            @RequestParam String type

    ) {
        String booking = bookingServiceImpl.createBooking(propertyId, bookingsDto, type);
        return booking;
    }

    @GetMapping("/getBookings")
    //localhost:8080/api/v1/bookings/getBookings
    public ResponseEntity<?> getBookings(@RequestParam String name
    ){
        List<Bookings> bookingsByEmail = bookingsRepository.findByName(name);
        return new ResponseEntity<>(bookingsByEmail,HttpStatus.OK);
    }


}
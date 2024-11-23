package com.hms.services;

import com.hms.payloads.BookingDto;

public interface BookingService {
    public String createBooking(long propertyId, BookingDto bookingsDto, String type);
}

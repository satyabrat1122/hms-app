package com.hms.implementation;

import com.hms.entity.Bookings;
import com.hms.entity.Property;
import com.hms.entity.Rooms;
import com.hms.payloads.BookingDto;
import com.hms.repository.BookingsRepository;
import com.hms.repository.PropertyRepository;
import com.hms.repository.RoomsRepository;
import com.hms.services.BookingService;
import com.hms.services.PdfService;
import com.hms.services.TwilioService;
import com.hms.services.WhatsAppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final PropertyRepository propertyRepository;
    private final RoomsRepository roomsRepository;
    private final BookingsRepository bookingsRepository;
    private PdfService pdfService;
    private TwilioService twilioService;
    private WhatsAppService whatsAppService;


    public BookingServiceImpl(PropertyRepository propertyRepository,
                              RoomsRepository roomsRepository,
                              BookingsRepository bookingsRepository, PdfService pdfService, TwilioService twilioService, WhatsAppService whatsAppService) {
        this.propertyRepository = propertyRepository;
        this.roomsRepository = roomsRepository;
        this.bookingsRepository = bookingsRepository;
        this.pdfService = pdfService;
        this.twilioService = twilioService;
        this.whatsAppService = whatsAppService;
    }

    @Override
    public String createBooking(long propertyId, BookingDto bookingsDto, String type) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Could not find Property"));
        List<Rooms> rooms = roomsRepository.findAvailableRooms(property, type, bookingsDto.getFromDate(), bookingsDto.getToDate());

        for (Rooms room : rooms) {
            if (room.getCount() == 0) {
                return "Rooms not available on: " + room.getDate();
            }

        }
        double basePrice = 0;
        for (Rooms room : rooms) {
            basePrice = room.getPerNightPrice() * (double) (rooms.size() - 1);
        }
        double gst=(basePrice*18)/100;
        double totalPrice= basePrice+gst;
        Bookings bookings = mapToEntity(bookingsDto);
        bookings.setBasePrice(basePrice);
        bookings.setTotalGST(gst);
        bookings.setTotalPrice(totalPrice);
        bookings.setProperty(property);

        Bookings savedBooking = bookingsRepository.save(bookings);
        if (savedBooking != null) {
            for (Rooms room : rooms) {
                room.setCount(room.getCount() - 1);
                roomsRepository.save(room);
            }

        }
        pdfService.generateBookingPdf("G:\\hms_bookings\\confirmation-order" + savedBooking.getId() + ".pdf", property, savedBooking);
      //  twilioService.sendSms("+917608825266",  "Booking Confirmed. Your booking id is: " + bookings.getId());
        whatsAppService.sendWhatsAppMessage("+917608825266", "+14155238886", "Booking Confirmed. Your booking id is: " + bookings.getId());
        return "Booking Created Successfully";
    }

    Bookings mapToEntity(BookingDto bookingDto){
        Bookings bookings=new Bookings();
        bookings.setName(bookingDto.getName());
        bookings.setEmailId(bookingDto.getEmailId());
        bookings.setToDate(bookingDto.getToDate());
        bookings.setFromDate(bookingDto.getFromDate());
        return bookings;
    }
}

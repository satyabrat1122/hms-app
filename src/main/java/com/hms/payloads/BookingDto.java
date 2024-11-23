package com.hms.payloads;

import com.hms.entity.Property;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDto {

    private String name;
    private String emailId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Property property;
    private Long property_id;
}
